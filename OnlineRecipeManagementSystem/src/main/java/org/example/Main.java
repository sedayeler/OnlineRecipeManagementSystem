package org.example;

import org.example.db.RecipeRepository;
import org.example.entities.abstracts.Recipe;
import org.example.entities.concretes.Category;
import org.example.entities.concretes.Tag;
import org.example.modules.creation.abstracts.RecipeFactory;
import org.example.modules.creation.concretes.*;
import org.example.modules.modification.concretes.ModifyRecipe;
import org.example.modules.search.concretes.SearchRecipe;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RecipeRepository recipeRepository = RecipeRepository.getInstance();
        RecipeFactory aegeanRegionFactory = new AegeanRegionFactory();
        RecipeFactory marmaraRegionFactory = new MarmaraRegionFactory();
        RecipeFactory blackSeaRegionFactory = new BlackSeaRegionFactory();
        RecipeFactory centralAnatoliaRegionFactory = new CentralAnatoliaRegionFactory();
        RecipeFactory easternAnatoliaRegionFactory = new SoutheasternAnatoliaRegionFactory();
        RecipeFactory southeasternAnatoliaRegionFactory = new SoutheasternAnatoliaRegionFactory();
        RecipeFactory mediterraneanRegionFactory = new MediterraneanRegionFactory();

        SearchRecipe searchRecipe = new SearchRecipe(recipeRepository);
        ModifyRecipe modifyRecipe = new ModifyRecipe(recipeRepository);

        while (true) {
            System.out.println("Please select an option:");
            System.out.println("1. Create Recipe");
            System.out.println("2. Search Recipes");
            System.out.println("3. Modify Recipe");
            System.out.println("4. Rate Recipe");
            System.out.println("5. Exit");


            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over

            switch (option) {
                case 1:
                    // Create Recipe
                    System.out.println("Enter recipe details:");
                    System.out.println("Enter name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter description:");
                    String description = scanner.nextLine();
                    System.out.println("Enter serving size:");
                    String servingSize = scanner.nextLine();
                    System.out.println("Enter ingredients:");
                    String ingredients = scanner.nextLine();
                    System.out.println("Enter instructions:");
                    String instructions = scanner.nextLine();

                    HashSet<Category> categories = new HashSet<>();
                    Category[] availableCategories = Category.values();
                    System.out.println("Please Choose the category of the recipe (up to 3 different Categories): ");
                    for (int i = 0; i < availableCategories.length; i++) {
                        System.out.println((i+1) + ". " + availableCategories[i].name());
                    }
                    for (int i = 0; i < 3; i++) {
                        System.out.println("Enter the number of a category:");
                        int categoryIndex = scanner.nextInt() - 1;
                        categories.add(availableCategories[categoryIndex]);
                    }

                    HashSet<Tag> tags = new HashSet<>();
                    Tag[] availableTags = Tag.values();
                    System.out.println("Please Choose the tag of the recipe (up to 3 different Tags): ");
                    for (int i = 0; i < availableTags.length; i++) {
                        System.out.println((i+1) + ". " + availableTags[i].name());
                    }
                    for (int i = 0; i < 3; i++) {
                        System.out.println("Enter the number of a tag:");
                        int tagIndex = scanner.nextInt() - 1;
                        tags.add(availableTags[tagIndex]);
                    }
                    System.out.println("Please Choose the region of the recipe: ");
                    System.out.println("1. Aegean Region");
                    System.out.println("2. Marmara Region");
                    System.out.println("3. Black Sea Region");
                    System.out.println("4. Central Anatolia Region");
                    System.out.println("5. Eastern Anatolia Region");
                    System.out.println("6. Southeastern Anatolia Region");
                    System.out.println("7. Mediterranean Region");
                    int region = scanner.nextInt();
                    RecipeFactory recipeFactory = null;
                    switch (region) {
                        case 1:
                            recipeFactory = aegeanRegionFactory;
                            break;
                        case 2:
                            recipeFactory = marmaraRegionFactory;
                            break;
                        case 3:
                            recipeFactory = blackSeaRegionFactory;
                            break;
                        case 4:
                            recipeFactory = centralAnatoliaRegionFactory;
                            break;
                        case 5:
                            recipeFactory = easternAnatoliaRegionFactory;
                            break;
                        case 6:
                            recipeFactory = southeasternAnatoliaRegionFactory;
                            break;
                        case 7:
                            recipeFactory = mediterraneanRegionFactory;
                            break;
                        default:
                            System.out.println("Invalid region. Please enter a number between 1 and 7.");
                            break;
                    }
                    recipeRepository.save(name, description, servingSize, categories, tags, ingredients, instructions, recipeFactory);
                    break;
                case 2:
                    // Search Recipes
                    System.out.println("Please select a search option:");
                    System.out.println("1. Search by name");
                    System.out.println("2. Search by tag");
                    System.out.println("3. Search by ingredient");
                    System.out.println("4. Search by category");
                    int searchOption = scanner.nextInt();
                    scanner.nextLine();  // Consume newline left-over
                    List<Recipe> foundRecipes = null;
                    switch (searchOption) {
                        case 1:
                            System.out.println("Enter a name to search:");
                            String nameOfSearch = scanner.nextLine();
                            foundRecipes = searchRecipe.findByName(nameOfSearch);
                            break;
                        case 2:
                            Tag[] availableTags1 = Tag.values();

                            HashSet<Tag> selectedTags = new HashSet<>();
                            for (int i = 0; i < 3; i++) {
                                System.out.println("Please Choose the tag of the recipe (up to 3 different Tags): ");
                                for (int j = 0; j < availableTags1.length; j++) {
                                    System.out.println((j+1) + ". " + availableTags1[j].name());
                                }
                                System.out.println("Enter the number of a tag (or enter 0 to finish):");
                                int tagIndex = scanner.nextInt() - 1;
                                if (tagIndex == -1) {
                                    break;
                                }
                                selectedTags.add(availableTags1[tagIndex]);
                                System.out.println("Selected tags: " + selectedTags);
                            }
                            foundRecipes = searchRecipe.findByTag(selectedTags);
                            break;
                        case 3:
                            System.out.println("Enter an ingredient to search:");
                            String ingredient = scanner.nextLine();
                            foundRecipes = searchRecipe.findByIngredient(ingredient);
                            break;
                        case 4:
                            Category[] availableCategories1 = Category.values();
                            HashSet<Category> selectedCategories = new HashSet<>();
                            for (int i = 0; i < 3; i++) {
                                System.out.println("Please Choose the category of the recipe (up to 3 different Categories): ");
                                for (int j = 0; j < availableCategories1.length; j++) {
                                    System.out.println((j+1) + ". " + availableCategories1[j].name());
                                }
                                System.out.println("Enter the number of a category (or enter 0 to finish):");
                                int categoryIndex = scanner.nextInt() - 1;
                                if (categoryIndex == -1) {
                                    break;
                                }
                                selectedCategories.add(availableCategories1[categoryIndex]);
                            }
                            foundRecipes = searchRecipe.findByCategory(selectedCategories);
                            break;
                        default:
                            System.out.println("Invalid option. Please enter a number between 1 and 4.");
                            break;
                    }
                    if (foundRecipes.isEmpty()) {
                        System.out.println("No recipes found with the selected option.");
                        break;
                    }
                    System.out.println("Search results:");
                    for (int i = 0; i < foundRecipes.size(); i++) {
                        System.out.println((i+1) + ". " + foundRecipes.get(i).getName());
                    }
                    System.out.println("Please select a recipe to view details:");
                    int recipeIndex = scanner.nextInt() - 1;
                    Recipe selectedRecipe = foundRecipes.get(recipeIndex);
                    System.out.println("Recipe details:");
                    System.out.println("Name: " + selectedRecipe.getName());
                    System.out.println("Description: " + selectedRecipe.getDescription());
                    System.out.println("Ingredients: " + selectedRecipe.getIngredients());
                    System.out.println("Instructions: " + selectedRecipe.getInstructions());
                    System.out.println("Impact by average rating: " + selectedRecipe.getAverageRating());
                    break;
                case 3:
                    // Modify Recipe
                    System.out.println("Please select a search option to find the recipe you want to modify:");
                    System.out.println("1. Search by name");
                    System.out.println("2. List all recipes");
                    int searchOption2 = scanner.nextInt();
                    scanner.nextLine();  // Consume newline left-over
                    List<Recipe> foundRecipesOfSearch2 = null;
                    switch (searchOption2) {
                        case 1:
                            System.out.println("Enter a name to search for the recipe you want to modify:");
                            String keywordToModify = scanner.nextLine();
                            foundRecipesOfSearch2 = searchRecipe.findByName(keywordToModify);
                            break;
                        case 2:
                            foundRecipesOfSearch2 = searchRecipe.printAll();
                            break;
                        default:
                            System.out.println("Invalid option. Please enter a number between 1 and 2.");
                            break;
                    }
                    if (foundRecipesOfSearch2.isEmpty()) {
                        System.out.println("No recipes found with the selected option.");
                        break;
                    }
                    System.out.println("Please select a recipe to modify:");
                    for (int i = 0; i < foundRecipesOfSearch2.size(); i++) {
                        System.out.println((i+1) + ". " + foundRecipesOfSearch2.get(i).getName());
                    }
                    int recipeIndex2 = scanner.nextInt() - 1;
                    Recipe selectedRecipe2 = foundRecipesOfSearch2.get(recipeIndex2);
                    System.out.println("Please select an attribute to modify:");
                    System.out.println("1. Ingredients");
                    System.out.println("2. Instructions");
                    System.out.println("3. Categories");
                    System.out.println("4. Tags");
                    int attributeOption = scanner.nextInt();
                    scanner.nextLine();  // Consume newline left-over
                    switch (attributeOption) {
                        case 1:
                            System.out.println("Enter new ingredients:");
                            String newIngredients = scanner.nextLine();
                            modifyRecipe.modifyIngredients(selectedRecipe2, newIngredients);
                            System.out.println("Ingredients have been modified.");
                            System.out.println("Do you want to undo the modification? Press 1 to undo, 0 to continue.");
                            int undoOption = scanner.nextInt();
                            if (undoOption == 1) {
                                modifyRecipe.undoModification(selectedRecipe2);
                                System.out.println("Modification has been undone.");
                            }
                            break;
                        case 2:
                            System.out.println("Enter new instructions:");
                            String newInstructions = scanner.nextLine();
                            modifyRecipe.modifyInstructions(selectedRecipe2, newInstructions);
                            System.out.println("Instructions have been modified.");
                            System.out.println("Do you want to undo the modification? Press 1 to undo, 0 to continue.");
                            int undoOption1 = scanner.nextInt();
                            if (undoOption1 == 1) {
                                modifyRecipe.undoModification(selectedRecipe2);
                                System.out.println("Modification has been undone.");
                            }
                            break;
                        case 3:
                            HashSet<Category> newCategories = new HashSet<>();
                            Category[] availableCategoriesToModify = Category.values();
                            System.out.println("Please Choose the category of the recipe (up to 3 different Categories): ");
                            for (int i = 0; i < availableCategoriesToModify.length; i++) {
                                System.out.println((i+1) + ". " + availableCategoriesToModify[i].name());
                            }
                            for (int i = 0; i < 3; i++) {
                                System.out.println("Enter the number of a category:");
                                int categoryIndex = scanner.nextInt() - 1;
                                newCategories.add(availableCategoriesToModify[categoryIndex]);
                            }
                            modifyRecipe.modifyCategories(selectedRecipe2, newCategories);
                            System.out.println("Categories have been modified.");
                            System.out.println("Do you want to undo the modification? Press 1 to undo, 0 to continue.");
                            int undoOption2 = scanner.nextInt();
                            if (undoOption2 == 1) {
                                modifyRecipe.undoModification(selectedRecipe2);
                                System.out.println("Modification has been undone.");
                            }
                            break;
                        case 4:
                            HashSet<Tag> newTags = new HashSet<>();
                            Tag[] availableTagsToModify = Tag.values();
                            System.out.println("Please Choose the tag of the recipe (up to 3 different Tags): ");
                            for (int i = 0; i < availableTagsToModify.length; i++) {
                                System.out.println((i+1) + ". " + availableTagsToModify[i].name());
                            }
                            for (int i = 0; i < 3; i++) {
                                System.out.println("Enter the number of a tag:");
                                int tagIndex = scanner.nextInt() - 1;
                                newTags.add(availableTagsToModify[tagIndex]);
                            }
                            modifyRecipe.modifyTags(selectedRecipe2, newTags);
                            System.out.println("Tags have been modified.");
                            System.out.println("Do you want to undo the modification? Press 1 to undo, 0 to continue.");
                            int undoOption3 = scanner.nextInt();
                            if (undoOption3 == 1) {
                                modifyRecipe.undoModification(selectedRecipe2);
                                System.out.println("Modification has been undone.");
                            }
                            break;
                        default:
                            System.out.println("Invalid option. Please enter a number between 1 and 7.");
                            break;
                    }
                    break;
                case 4:
                    // Rate Recipe
                    System.out.println("Please select a search option to rate recipe:");
                    System.out.println("1. Search by name");
                    System.out.println("2. List all recipes");
                    int searchOption1 = scanner.nextInt();
                    scanner.nextLine();  // Consume newline left-over
                    List<Recipe> foundRecipesOfSearch = null;
                    switch (searchOption1) {
                        case 1:
                            System.out.println("Enter a name to search for the recipe you want to rate:");
                            String keywordToRate = scanner.nextLine();
                            foundRecipesOfSearch = searchRecipe.findByName(keywordToRate);
                            break;
                        case 2:
                            foundRecipesOfSearch = searchRecipe.printAll();
                            break;
                        default:
                            System.out.println("Invalid option. Please enter a number between 1 and 2.");
                            break;
                    }
                    if (foundRecipesOfSearch.isEmpty()) {
                        System.out.println("No recipes found with the selected option.");
                        break;
                    }
                    System.out.println("Please select a recipe to rate:");
                    for (int i = 0; i < foundRecipesOfSearch.size(); i++) {
                        System.out.println((i+1) + ". " + foundRecipesOfSearch.get(i).getName());
                    }
                    int recipeIndex1 = scanner.nextInt() - 1;
                    Recipe selectedRecipe1 = foundRecipesOfSearch.get(recipeIndex1);
                    System.out.println("Enter your rating (1-5) for " + selectedRecipe1.getName() + ":");
                    int rating = scanner.nextInt();
                    // Rate the recipe
                    recipeRepository.rateRecipe(selectedRecipe1, rating);
                    break;
                case 5:
                    // Exit
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please enter a number between 1 and 4.");
            }
        }
    }
}