package com.molovataconstantin.flavormetrics.exception.Impl;

import org.springframework.security.access.AccessDeniedException;

public class ApiAccessDeniedException extends AccessDeniedException {

    public ApiAccessDeniedException(String msg) {
        super(msg);
    }

    public ApiAccessDeniedException(String msg, Throwable cause) {
        super(msg, cause);
    }
}