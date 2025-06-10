package com.molovataconstantin.flavormetrics.repository;

import com.molovataconstantin.flavormetrics.entity.Jwt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JWTRepository extends JpaRepository<Jwt, UUID> {
}