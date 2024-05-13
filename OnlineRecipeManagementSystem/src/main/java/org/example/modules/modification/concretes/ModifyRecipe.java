package org.example.modules.modification.concretes;

import org.example.db.RecipeRepository;
import org.example.entities.abstracts.Recipe;
import org.example.entities.concretes.Category;
import org.example.entities.concretes.Tag;

import java.util.HashSet;
import java.util.List;

public class ModifyRecipe {
    private  RecipeRepository recipeRepository;

    public ModifyRecipe(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    public void modifyIngredients(Recipe recipe, String ingredients){
        recipeRepository.modifyRecipeIngredients(recipe, ingredients);
    }

    public void modifyInstructions(Recipe recipe, String instructions){
        recipeRepository.modifyRecipeInstructions(recipe, instructions);
    }

    public void modifyCategories(Recipe recipe, HashSet<Category> categories){
        recipeRepository.modifyRecipeCategories(recipe, categories);
    }

    public void modifyTags(Recipe recipe, HashSet<Tag> tags){
        recipeRepository.modifyRecipeTags(recipe, tags);
    }

    public void undoModification(Recipe recipe){
        recipeRepository.undoModification(recipe);
    }

    public void printAll(){
        recipeRepository.listAll();
    }
}
