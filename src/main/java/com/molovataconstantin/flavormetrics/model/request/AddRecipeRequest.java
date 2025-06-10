package com.molovataconstantin.flavormetrics.model.request;

import com.molovataconstantin.flavormetrics.model.IngredientDto;
import com.molovataconstantin.flavormetrics.model.enums.AllergyType;
import com.molovataconstantin.flavormetrics.model.enums.DietaryPreferenceType;
import com.molovataconstantin.flavormetrics.model.enums.DifficultyType;
import com.molovataconstantin.flavormetrics.model.enums.TagType;

import java.util.List;

public record AddRecipeRequest(
        String name,
        List<IngredientDto> ingredients,
        String imageUrl,
        String instructions,
        Integer prepTimeMinutes,
        Integer cookTimeMinutes,
        DifficultyType difficulty,
        Integer estimatedCalories,
        List<TagType> tags,
        List<AllergyType> allergies,
        List<DietaryPreferenceType> dietaryPreferences) {
}
