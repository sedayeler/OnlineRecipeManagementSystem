package org.example.modules.rating.concretes;

import org.example.db.RecipeRepository;
import org.example.entities.abstracts.Recipe;
import org.example.modules.rating.abstracts.RatingStrategy;

public class TotalRatingStrategy implements RatingStrategy {
    @Override
    public void updateRatings(Recipe recipe, int rating) {
        recipe.setTotalRating((recipe.getTotalRating() + 1));
    }

    @Override
    public double computeImpact(Recipe recipe) {

        return recipe.getTotalRating();
    }
}
