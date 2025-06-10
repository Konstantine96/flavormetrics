package com.molovataconstantin.flavormetrics.service;

import com.molovataconstantin.flavormetrics.model.Data;
import com.molovataconstantin.flavormetrics.model.request.LoginRequest;
import com.molovataconstantin.flavormetrics.model.request.RegisterRequest;
import com.molovataconstantin.flavormetrics.model.response.LoginResponse;
import com.molovataconstantin.flavormetrics.model.response.RegisterResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

public interface AuthService {
    Data<RegisterResponse> registerUser(RegisterRequest data, Authentication authentication);

    LoginResponse authenticate(LoginRequest data, Authentication authentication);

    String logout(HttpServletResponse response);
}
