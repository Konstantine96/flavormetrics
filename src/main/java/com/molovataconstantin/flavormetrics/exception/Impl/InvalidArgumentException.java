package com.molovataconstantin.flavormetrics.exception.Impl;

import com.molovataconstantin.flavormetrics.exception.ApiException;
import org.springframework.http.HttpStatusCode;

public class InvalidArgumentException extends ApiException {
    public InvalidArgumentException(String message, String description, HttpStatusCode httpStatusCode, String path) {
        super(message, description, httpStatusCode, path);
    }
}