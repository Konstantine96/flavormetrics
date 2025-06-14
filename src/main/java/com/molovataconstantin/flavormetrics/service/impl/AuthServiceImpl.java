package com.molovataconstantin.flavormetrics.service.impl;

import com.molovataconstantin.flavormetrics.entity.User;
import com.molovataconstantin.flavormetrics.exception.Impl.DuplicateEmailException;
import com.molovataconstantin.flavormetrics.exception.Impl.NotAllowedRequestException;
import com.molovataconstantin.flavormetrics.factory.UserFactory;
import com.molovataconstantin.flavormetrics.model.Data;
import com.molovataconstantin.flavormetrics.model.enums.RoleType;
import com.molovataconstantin.flavormetrics.model.request.LoginRequest;
import com.molovataconstantin.flavormetrics.model.request.RegisterRequest;
import com.molovataconstantin.flavormetrics.model.response.LoginResponse;
import com.molovataconstantin.flavormetrics.model.response.RegisterResponse;
import com.molovataconstantin.flavormetrics.repository.UserRepository;
import com.molovataconstantin.flavormetrics.service.AuthService;
import com.molovataconstantin.flavormetrics.service.JwtService;
import com.molovataconstantin.flavormetrics.util.ModelConverter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthServiceImpl implements AuthService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserFactory userFactory;

    public AuthServiceImpl(
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            UserRepository userRepository, UserFactory userFactory) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    @Override
    @Transactional
    public Data<RegisterResponse> registerUser(RegisterRequest data,
                                               Authentication authentication) {
        if (data == null) {
            throw new NotAllowedRequestException(
                    "Bad request",
                    "Missing data",
                    HttpStatus.BAD_REQUEST,
                    "data");
        }
        if (data.role() == RoleType.ROLE_ADMIN) {
            throw new NotAllowedRequestException(
                    "Bad request",
                    "Cannot perform register action because you do not have authorization to create admin users",
                    HttpStatus.BAD_REQUEST,
                    "data.role");
        }
        final boolean isEmailUsed = userRepository.existsByUsername_Value(data.username());
        if (isEmailUsed) {
            throw new DuplicateEmailException(
                    "Invalid email",
                    "This email address is not available",
                    HttpStatus.CONFLICT,
                    "data.email");
        }
        final boolean isAuthenticated = authentication != null && authentication.isAuthenticated();
        if (isAuthenticated) {
            throw new NotAllowedRequestException(
                    "Bad request",
                    "Cannot perform register action because user is already authenticated",
                    HttpStatus.BAD_REQUEST,
                    "");
        }

        var user = userFactory.createUser(data);
        user = userRepository.save(user);
        LOGGER.info("User created: {}", user);
        return Data.body(ModelConverter.registerResponse(user));
    }

    @Override
    public LoginResponse authenticate(LoginRequest data, Authentication authentication) {
        final boolean isAuthenticated;
        if (authentication == null) {
            isAuthenticated = false;
        } else {
            isAuthenticated = authentication.isAuthenticated();
        }
        if (isAuthenticated) {
            throw new NotAllowedRequestException(
                    "Invalid request",
                    "Cannot perform register action because user is already authenticated",
                    HttpStatus.BAD_REQUEST,
                    "");
        }
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                data.email(),
                data.password());
        authenticationManager.authenticate(authToken);
        User user = userRepository.getByUsername_Value(data.email());
        String jwtToken = jwtService.generateToken(user);
        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        return new LoginResponse(user.getUsername(), roles, jwtToken);
    }

    @Override
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("Authorization", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return "Logout success!";
    }
}
