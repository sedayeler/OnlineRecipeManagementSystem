package org.example.entities.concretes;


import org.example.entities.abstracts.Recipe;

import java.util.HashSet;

public class CentralAnatoliaRegionRecipe extends Recipe {

    public CentralAnatoliaRegionRecipe(String name, String description, String servingSize, HashSet<Category> categories, HashSet<Tag> tags, String ingredients, String instructions) {
        super(name, description, servingSize, categories, tags, ingredients, instructions);
    }
}
