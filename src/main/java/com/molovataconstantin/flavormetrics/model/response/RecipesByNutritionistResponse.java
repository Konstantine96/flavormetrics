package com.molovataconstantin.flavormetrics.model.response;

import com.molovataconstantin.flavormetrics.model.RecipeDto;

import java.util.List;

public record RecipesByNutritionistResponse(String username, List<RecipeDto> recipes) {
}