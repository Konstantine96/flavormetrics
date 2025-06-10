package com.molovataconstantin.flavormetrics.factory;

import com.molovataconstantin.flavormetrics.entity.Allergy;
import com.molovataconstantin.flavormetrics.entity.Impl.Nutritionist;
import com.molovataconstantin.flavormetrics.entity.Ingredient;
import com.molovataconstantin.flavormetrics.entity.Recipe;
import com.molovataconstantin.flavormetrics.entity.Tag;
import com.molovataconstantin.flavormetrics.model.request.AddRecipeRequest;
import com.molovataconstantin.flavormetrics.util.ModelConverter;

import java.time.LocalDateTime;
import java.util.List;

public final class RecipeFactory {

    private RecipeFactory() {
        // Prevent instantiation
    }

    public static Recipe getRecipe(AddRecipeRequest data, Nutritionist nutritionist) {
        final List<Ingredient> ingredients = data.ingredients()
                .stream()
                .map(ModelConverter::toIngredient)
                .toList();
        final Recipe recipe = new Recipe();
        final List<Tag> tags = data.tags().stream()
                .map(tagType -> {
                    Tag tag = ModelConverter.toTag(tagType);
                    tag.getRecipes().add(recipe);
                    return tag;
                })
                .toList();
        final List<Allergy> allergies = data.allergies()
                .stream()
                .map(allergyDto -> {
                    Allergy allergy = ModelConverter.toAllergy(allergyDto);
                    allergy.setRecipe(recipe);
                    return allergy;
                })
                .toList();
        recipe.setNutritionist(nutritionist);
        recipe.setInstructions(data.instructions());
        recipe.setUpdatedAt(LocalDateTime.now());
        recipe.setImageUrl(data.imageUrl());
        recipe.setCookTimeMinutes(data.cookTimeMinutes());
        recipe.setDifficulty(data.difficulty());
        recipe.setEstimatedCalories(data.estimatedCalories());
        recipe.setPrepTimeMinutes(data.prepTimeMinutes());
        recipe.setTags(tags);
        recipe.setIngredients(ingredients);
        recipe.setName(data.name());
        recipe.setAllergies(allergies);
        recipe.setDietaryPreferences(data.dietaryPreferences());
        return recipe;
    }
}