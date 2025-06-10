package com.molovataconstantin.flavormetrics.service;

import com.molovataconstantin.flavormetrics.model.Data;
import com.molovataconstantin.flavormetrics.model.ProfileDto;
import com.molovataconstantin.flavormetrics.model.request.CreateProfileRequest;
import org.springframework.security.core.Authentication;

public interface ProfileService {
    Data<ProfileDto> getProfile(Authentication authentication);

    Data<ProfileDto> createProfile(CreateProfileRequest data, Authentication authentication);;
}