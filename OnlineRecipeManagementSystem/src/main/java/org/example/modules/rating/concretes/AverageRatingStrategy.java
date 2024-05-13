package org.example.modules.rating.concretes;

import org.example.db.RecipeRepository;
import org.example.entities.abstracts.Recipe;
import org.example.modules.rating.abstracts.RatingStrategy;

public class AverageRatingStrategy implements RatingStrategy {

    @Override
    public void updateRatings(Recipe recipe, int rating) {
        recipe.setAverageRating(((recipe.getAverageRating() * (recipe.getTotalRating()- 1) + rating) / recipe.getTotalRating()));
    }

    @Override
    public double computeImpact(Recipe recipe) {
        return recipe.getAverageRating();
    }

}
