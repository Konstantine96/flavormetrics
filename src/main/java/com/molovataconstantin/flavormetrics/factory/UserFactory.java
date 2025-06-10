package com.molovataconstantin.flavormetrics.factory;

import com.molovataconstantin.flavormetrics.entity.Authority;
import com.molovataconstantin.flavormetrics.entity.Email;
import com.molovataconstantin.flavormetrics.entity.Impl.Admin;
import com.molovataconstantin.flavormetrics.entity.Impl.Nutritionist;
import com.molovataconstantin.flavormetrics.entity.Impl.RegularUser;
import com.molovataconstantin.flavormetrics.entity.User;
import com.molovataconstantin.flavormetrics.model.request.RegisterRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserFactory {
    private final PasswordEncoder passwordEncoder;

    public UserFactory(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public User createUser(RegisterRequest request) {
        if (request == null || request.role() == null) {
            return null;
        }
        return switch (request.role()) {
            case ROLE_ADMIN -> createAdminUser(request);
            case ROLE_USER -> createRegularUser(request);
            case ROLE_NUTRITIONIST -> createNutritionistUser(request);
        };
    }

    private User createNutritionistUser(RegisterRequest request) {
        final Email email = new Email(request.username());
        final User user = new Nutritionist(passwordEncoder.encode(request.password()), email);
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setUpdatedAt(LocalDateTime.now());
        final Authority authority = new Authority(request.role());
        authority.setUser(user);
        user.getAuthorities().add(authority);
        return user;
    }

    private User createRegularUser(RegisterRequest request) {
        final Email email = new Email(request.username());
        final User user = new RegularUser(passwordEncoder.encode(request.password()), email);
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setUpdatedAt(LocalDateTime.now());
        final Authority authority = new Authority(request.role());
        authority.setUser(user);
        user.getAuthorities().add(authority);
        return user;
    }

    private User createAdminUser(RegisterRequest request) {
        final Email email = new Email(request.username());
        final User user = new Admin(passwordEncoder.encode(request.password()), email);
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setUpdatedAt(LocalDateTime.now());
        final Authority authority = new Authority(request.role());
        authority.setUser(user);
        user.getAuthorities().add(authority);
        return user;
    }
}