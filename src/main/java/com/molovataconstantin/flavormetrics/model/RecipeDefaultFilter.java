package com.molovataconstantin.flavormetrics.model;

import com.molovataconstantin.flavormetrics.model.enums.DifficultyType;

public record RecipeDefaultFilter(
        int prepTimeMinutes,
        int cookTimeMinutes,
        int estimatedCalories,
        DifficultyType difficultyType
) {
}