package com.molovataconstantin.flavormetrics.service;

import com.molovataconstantin.flavormetrics.model.Data;
import com.molovataconstantin.flavormetrics.model.ProfileFilter;
import com.molovataconstantin.flavormetrics.model.RecipeDefaultFilter;
import com.molovataconstantin.flavormetrics.model.RecipeDto;
import com.molovataconstantin.flavormetrics.model.request.AddRecipeRequest;
import com.molovataconstantin.flavormetrics.model.response.RecipesByNutritionistResponse;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.UUID;

public interface RecipeService {
    Data<RecipeDto> add(AddRecipeRequest data, Authentication authentication);

    Data<RecipeDto> getById(UUID id);

    Data<RecipesByNutritionistResponse> getByNutritionist(String username);

    Data<RecipeDto> updateById(UUID id, AddRecipeRequest data, Authentication authentication);

    Data<String> deleteById(UUID id, Authentication authentication);

    Data<List<RecipeDto>> getAll();

    ProfileFilter getProfilePreferences(String username);

    Data<List<RecipeDto>> findAllByProfilePreferences(ProfileFilter profileFilter);

    Data<List<RecipeDto>> findAllByDefaultFilter(RecipeDefaultFilter recipeDefaultFilter);

    boolean existsById(UUID id);

    Data<RecipeDto> updateImageById(UUID id, String url);
}