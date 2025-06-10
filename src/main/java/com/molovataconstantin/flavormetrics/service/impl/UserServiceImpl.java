package com.molovataconstantin.flavormetrics.service.impl;

import com.molovataconstantin.flavormetrics.model.Data;
import com.molovataconstantin.flavormetrics.model.user.UserDto;
import com.molovataconstantin.flavormetrics.model.user.impl.NutritionistDto;
import com.molovataconstantin.flavormetrics.model.user.impl.RegularUserDto;
import com.molovataconstantin.flavormetrics.repository.NutritionistRepository;
import com.molovataconstantin.flavormetrics.repository.RegularUserRepository;
import com.molovataconstantin.flavormetrics.repository.UserRepository;
import com.molovataconstantin.flavormetrics.service.UserService;
import com.molovataconstantin.flavormetrics.util.ModelConverter;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final NutritionistRepository nutritionistRepository;
    private final RegularUserRepository regularUserRepository;

    public UserServiceImpl(UserRepository userRepository,
                           NutritionistRepository nutritionistRepository,
                           RegularUserRepository regularUserRepository) {
        this.userRepository = userRepository;
        this.nutritionistRepository = nutritionistRepository;
        this.regularUserRepository = regularUserRepository;
    }

    @Override
    public Data<List<UserDto>> getAllUsers() {
        List<UserDto> users = userRepository.findAll()
                .stream()
                .map(ModelConverter::toUserDto)
                .toList();
        return Data.body(users);
    }

    @Override
    public Data<List<RegularUserDto>> getAllRegularUsers() {
        List<RegularUserDto> regularUsers = regularUserRepository.findAll()
                .stream()
                .map(ModelConverter::toRegularUserDto)
                .toList();
        return Data.body(regularUsers);
    }

    @Override
    public Data<List<NutritionistDto>> getAllNutritionistUsers() {
        List<NutritionistDto> nutritionists = nutritionistRepository.findAll()
                .stream()
                .map(ModelConverter::toNutritionistDto)
                .toList();
        return Data.body(nutritionists);
    }
}