package com.molovataconstantin.flavormetrics.model;

public record Data<T>(T data) {

    public static <T> Data<T> body(T body) {
        return new Data<>(body);
    }
}