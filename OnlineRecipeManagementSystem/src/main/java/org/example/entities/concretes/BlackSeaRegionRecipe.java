package org.example.entities.concretes;

import org.example.entities.abstracts.Recipe;

import java.util.HashSet;

public class BlackSeaRegionRecipe extends Recipe {

    public BlackSeaRegionRecipe(String name, String description, String servingSize, HashSet<Category> categories, HashSet<Tag> tags, String ingredients, String instructions) {
        super(name, description, servingSize, categories, tags, ingredients, instructions);
    }
}
