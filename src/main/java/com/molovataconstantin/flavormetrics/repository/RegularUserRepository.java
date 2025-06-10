package com.molovataconstantin.flavormetrics.repository;

import com.molovataconstantin.flavormetrics.entity.Impl.RegularUser;
import com.molovataconstantin.flavormetrics.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RegularUserRepository extends JpaRepository<RegularUser, UUID> {
    User findByUsername_Value(String usernameValue);

    boolean existsByUsername_Value(String usernameValue);

    Optional<RegularUser> getByUsername_Value(String usernameValue);
}