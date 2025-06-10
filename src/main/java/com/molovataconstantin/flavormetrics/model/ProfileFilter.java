package com.molovataconstantin.flavormetrics.model;

import com.molovataconstantin.flavormetrics.entity.Allergy;
import com.molovataconstantin.flavormetrics.model.enums.DietaryPreferenceType;

import java.util.List;

public record ProfileFilter(DietaryPreferenceType dietaryPreference,
                            List<Allergy> allergies) {

    public List<String> allergiesToString() {
        return allergies.stream()
                .map(Allergy::getName)
                .toList();
    }
}
