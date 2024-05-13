package org.example.modules.search.concretes;

import org.example.db.RecipeRepository;
import org.example.entities.abstracts.Recipe;
import org.example.entities.concretes.Category;
import org.example.entities.concretes.Tag;

import java.util.HashSet;
import java.util.List;

public class SearchRecipe {
   private RecipeRepository recipeRepository;

    public SearchRecipe(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> findByTag(HashSet<Tag> tag){
        return recipeRepository.findByTag(tag);
    }

    public List<Recipe> findByCategory(HashSet<Category> category){
        return recipeRepository.findByCategories(category);
    }
    public List<Recipe> findByIngredient(String ingredient){
        return recipeRepository.findByIngredient(ingredient);
    }
    public List<Recipe> findByName(String name){
        return recipeRepository.findByName(name);
    }

    public List<Recipe> printAll(){
        return recipeRepository.listAll();
    }


}
