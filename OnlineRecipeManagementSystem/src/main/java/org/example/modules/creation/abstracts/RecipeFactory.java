package org.example.modules.creation.abstracts;

import org.example.entities.abstracts.Recipe;
import org.example.entities.concretes.Category;
import org.example.entities.concretes.Tag;

import java.util.HashSet;

public abstract class RecipeFactory {
    public abstract Recipe createRecipe(String name,
                                        String description,
                                        String servingSize,
                                        HashSet<Category> categories,
                                        HashSet<Tag> tags,
                                        String ingredients,
                                        String instructions);

    public abstract String getRegionName();
}
