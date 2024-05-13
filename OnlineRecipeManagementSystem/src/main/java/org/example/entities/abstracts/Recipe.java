package org.example.entities.abstracts;

import org.example.entities.concretes.Category;
import org.example.entities.concretes.Tag;
import org.example.modules.rating.abstracts.RatingStrategy;

import java.util.HashSet;

public abstract class Recipe implements Cloneable{
    private String name;
    private String description;
    private HashSet<Category> categories;
    private HashSet<Tag> tags;
    private String ingredients;
    private String instructions;
    private String servingSize;
    private int totalRating;
    private double averageRating;


    public Recipe(String name, String description, String servingSize, HashSet<Category> categories, HashSet<Tag> tags, String ingredients, String instructions) {
        this.name = name;
        this.description = description;
        this.servingSize = servingSize;
        this.tags = tags;
        this.categories = categories;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.totalRating = 0;
        this.averageRating = 0;

    }


    public void setTotalRating(int totalRating) {
        this.totalRating = totalRating;
    }
    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getTotalRating() {
        return totalRating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashSet<Category> getCategories() {
        return categories;
    }

    public void setCategories(HashSet<Category> categories) {
        this.categories = categories;
    }

    public HashSet<Tag> getTags() {
        return this.tags;
    }

    public void setTags(HashSet<Tag> tags) {
        this.tags = tags;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getServingSize() {
        return servingSize;
    }

    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        Recipe cloned = (Recipe) super.clone();
        cloned.categories = new HashSet<>(this.categories);
        cloned.tags = new HashSet<>(this.tags);
        cloned.ingredients = new String(this.ingredients);
        cloned.instructions = new String(this.instructions);
        return cloned;
    }




}
