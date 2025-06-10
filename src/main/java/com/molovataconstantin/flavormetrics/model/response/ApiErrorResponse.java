package com.molovataconstantin.flavormetrics.model.response;

public record ApiErrorResponse(String message, String description,
                               int status, String path) {

}