package org.example.modules.creation.concretes;

import org.example.entities.abstracts.Recipe;
import org.example.entities.concretes.AegeanRegionRecipe;
import org.example.entities.concretes.Category;
import org.example.entities.concretes.Tag;
import org.example.modules.creation.abstracts.RecipeFactory;

import java.util.HashSet;

public class AegeanRegionFactory  extends RecipeFactory {
    private Recipe recipe;
    @Override
    public Recipe createRecipe(String name, String description, String servingSize, HashSet<Category> categories, HashSet<Tag> tags, String ingredients, String instructions) {
        if (categories.size() > 3) {
            throw new IllegalArgumentException("You can only assign up to 3 categories.");
        }
        if (tags.size() > 3) {
            throw new IllegalArgumentException("You can only assign up to 3 tags.");
        }
        recipe = new AegeanRegionRecipe(name, description, servingSize, categories, tags, ingredients, instructions);
        return recipe;
    }

    @Override
    public String getRegionName() {
        return "Aegean Region";
    }
}
