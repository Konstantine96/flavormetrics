package com.molovataconstantin.flavormetrics.service;

import com.molovataconstantin.flavormetrics.model.Data;
import com.molovataconstantin.flavormetrics.model.RatingDto;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.UUID;

public interface RatingService {
    Data<String> addRecipeRating(UUID recipeId, int rating, Authentication authentication);

    Data<List<RatingDto>> getAllRatingsByRecipeId(UUID recipeId);

    Data<List<RatingDto>> getAllRatingsByUser(Authentication authentication);
}