package org.example.modules.rating.abstracts;

import org.example.entities.abstracts.Recipe;

public interface RatingStrategy {
    void updateRatings(Recipe recipe, int rate);
    double computeImpact(Recipe recipe);


}
