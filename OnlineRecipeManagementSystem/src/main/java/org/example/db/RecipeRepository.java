package org.example.db;

import org.example.entities.abstracts.Recipe;
import org.example.entities.concretes.*;
import org.example.modules.creation.abstracts.RecipeFactory;
import org.example.modules.rating.abstracts.RatingStrategy;
import org.example.modules.rating.concretes.AverageRatingStrategy;
import org.example.modules.rating.concretes.TotalRatingStrategy;


import java.util.*;
import java.util.stream.Collectors;

public class RecipeRepository {

    private static RecipeRepository instance;
    private LinkedList<Recipe> recipes = new LinkedList<>();
    private Stack<Recipe> history = new Stack<>();
    private RatingStrategy averageRatingStrategy = new AverageRatingStrategy();
    private RatingStrategy totalRatingStrategy = new TotalRatingStrategy();


    private RecipeRepository() {

    }

    public static RecipeRepository getInstance() {
        if (instance == null) {
            instance = new RecipeRepository();
        }
        return instance;
    }

    public void savePreviousState(Recipe recipe) {
        try {
            history.push((Recipe) recipe.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void undoModification(Recipe recipe) {
        if (!history.isEmpty()) {
            Recipe previousState = history.pop();
            recipe.setName(previousState.getName());
            recipe.setDescription(previousState.getDescription());
            recipe.setServingSize(previousState.getServingSize());
            recipe.setCategories(previousState.getCategories());
            recipe.setTags(previousState.getTags());
            recipe.setIngredients(previousState.getIngredients());
            recipe.setInstructions(previousState.getInstructions());
        }
    }

    public List<Recipe> listAll (){
        return recipes;
    }

    public List<Recipe> findByTag(HashSet<Tag> tags) {
        List<Recipe> foundRecipes = new LinkedList<>();
        for (Recipe recipe : recipes) {
            HashSet<Tag> recipeTags = recipe.getTags();
            for (Tag tag : tags) {
                if (recipeTags.contains(tag)) {
                    foundRecipes.add(recipe);
                    break;
                }
            }
        }
        return foundRecipes;
    }

    public List<Recipe> findByCategories(HashSet<Category> categories) {
        List<Recipe> foundRecipes = new LinkedList<>();
        for (Recipe recipe : recipes) {
            HashSet<Category> recipeCategories = recipe.getCategories();
            for (Category category : categories) {
                if (recipeCategories.contains(category)) {
                    foundRecipes.add(recipe);
                    break;
                }
            }
        }
        return foundRecipes;
    }

    public List<Recipe> findByIngredient(String ingredient) {
        List<Recipe> foundRecipes = new LinkedList<>();
        ingredient = ingredient.toLowerCase();
        for (Recipe recipe : recipes) {
            if (recipe.getIngredients().toLowerCase().contains(ingredient)) {
                foundRecipes.add(recipe);
                System.out.println("Recipe name : " + recipe.getName() + " has been found with the ingredient : " + ingredient);
            } else
                System.out.println("Recipe name : " + recipe.getName() + " has not been found with the ingredient : " + ingredient);

        }
        return foundRecipes;
    }

    public List<Recipe> findByName(String keyword) {
        List<Recipe> foundRecipes = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getName().toLowerCase().contains(keyword.toLowerCase())) {
                foundRecipes.add(recipe);
            }
        }
        return foundRecipes;
    }

    public void modifyRecipeIngredients(Recipe recipe, String ingredients) {
        savePreviousState(recipe);
        recipe.setIngredients(ingredients);

        System.out.println("Recipe ingredients have been modified." + " MODIFIED INGREDIENTS : " + ingredients);
    }

    public void modifyRecipeInstructions(Recipe recipe, String instructions) {
        savePreviousState(recipe);
        recipe.setInstructions(instructions);
        System.out.println("Recipe instructions have been modified. " + " MODIFIED INSTRUCTIONS : " + instructions);
    }

    public void modifyRecipeCategories(Recipe recipe, HashSet<Category> categories) {
        savePreviousState(recipe);
        recipe.getCategories().clear();
        recipe.setCategories(categories);
        System.out.println("Recipe categories have been modified." + " Modified categories : " + categories.size());
        for (Category category : categories) {
            System.out.println(category.name());
        }
    }

    public void modifyRecipeTags(Recipe recipe, HashSet<Tag> tags) {
        savePreviousState(recipe);
        recipe.getTags().clear();
        recipe.setTags(tags);
        System.out.println("Recipe tags have been modified." + " Modified tags : " + tags.size());
        for (Tag tag : tags) {
            System.out.println(tag.name());
        }
    }

    public void save(String name, String description, String servingSize, HashSet<Category> categories, HashSet<Tag> tags, String ingredients, String instructions, RecipeFactory recipeFactory) {
        Recipe recipe = recipeFactory.createRecipe(name, description, servingSize, categories, tags, ingredients, instructions);
        recipes.add(recipe);
        if (categories.size() > 3) {
            throw new IllegalArgumentException("You can only assign up to 3 categories.");
        }
        if (tags.size() > 3) {
            throw new IllegalArgumentException("You can only assign up to 3 tags.");
        }
        System.out.println("Recipe name : " + name + " has been added to the repository.");
        System.out.println("Recipe description : " + description);
        System.out.println("Recipe serving size : " + servingSize);
        System.out.println("Recipe categories : " + categories.stream().map(Category::name).collect(Collectors.toList()));
        System.out.println("Recipe tags : " + tags.stream().map(Tag::name).collect(Collectors.toList()));
        System.out.println("Recipe ingredients : " + ingredients);
        System.out.println("Recipe instructions : " + instructions);
        System.out.println("Region of recipe : " + recipeFactory.getRegionName());


    }

    public void rateRecipe(Recipe recipe, int rating) {
        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5.");
        }

        if (recipe.getTotalRating() == 0){
            System.out.println("This is the first rating for this recipe.");
            recipe.setTotalRating(1);
            recipe.setAverageRating(rating);
            System.out.println("Recipe has been rated with " + rating + " points.");
            System.out.println("New total rating : " + recipe.getTotalRating());
            System.out.println( "New average rating : " + recipe.getAverageRating());
        }else {
            totalRatingStrategy.updateRatings(recipe, rating);
            totalRatingStrategy.computeImpact(recipe);
            averageRatingStrategy.updateRatings(recipe, rating);
            averageRatingStrategy.computeImpact(recipe);
            System.out.println("Recipe has been rated with " + rating + " points.");
            System.out.println("New total rating : " + recipe.getTotalRating());
            System.out.println( "New average rating : " + recipe.getAverageRating());

        }




    }

}











