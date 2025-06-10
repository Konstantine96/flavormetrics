package com.molovataconstantin.flavormetrics.model.response;

import com.molovataconstantin.flavormetrics.model.IngredientDto;

import java.util.List;
import java.util.UUID;

public record AddRecipeResponse(
        UUID id,
        String name,
        String nutritionist,
        List<IngredientDto> ingredients
) {
}