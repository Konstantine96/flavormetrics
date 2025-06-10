package com.molovataconstantin.flavormetrics.exception.Impl;

import com.molovataconstantin.flavormetrics.exception.ApiException;
import org.springframework.http.HttpStatusCode;

public class ProfileNotFoundException extends ApiException {

    public ProfileNotFoundException(String message, String description, HttpStatusCode httpStatusCode, String path) {
        super(message, description, httpStatusCode, path);
    }
}