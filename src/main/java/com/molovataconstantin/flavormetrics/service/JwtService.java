package com.molovataconstantin.flavormetrics.service;

import com.molovataconstantin.flavormetrics.entity.User;
import com.nimbusds.jose.jwk.RSAKey;


import java.util.UUID;

public interface JwtService {
    String generateToken(User details);

    UUID getId();

    RSAKey getPublicKey();

    boolean isTokenValid(String token);

    String extractUsername(String token);
}