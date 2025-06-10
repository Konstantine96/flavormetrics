package com.molovataconstantin.flavormetrics.model.request;


import com.molovataconstantin.flavormetrics.model.enums.AllergyType;
import com.molovataconstantin.flavormetrics.model.enums.DietaryPreferenceType;

import java.util.List;

public record CreateProfileRequest(DietaryPreferenceType dietaryPreference,
                                   List<AllergyType> allergies) {
}
