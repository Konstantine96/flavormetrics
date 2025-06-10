package com.molovataconstantin.flavormetrics.service;

import com.molovataconstantin.flavormetrics.model.Data;
import com.molovataconstantin.flavormetrics.model.user.UserDto;
import com.molovataconstantin.flavormetrics.model.user.impl.NutritionistDto;
import com.molovataconstantin.flavormetrics.model.user.impl.RegularUserDto;

import java.util.List;

public interface UserService {
    Data<List<UserDto>> getAllUsers();

    Data<List<RegularUserDto>> getAllRegularUsers();

    Data<List<NutritionistDto>> getAllNutritionistUsers();
}