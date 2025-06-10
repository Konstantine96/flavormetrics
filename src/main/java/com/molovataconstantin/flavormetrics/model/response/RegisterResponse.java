package com.molovataconstantin.flavormetrics.model.response;

import com.molovataconstantin.flavormetrics.model.enums.RoleType;

import java.util.UUID;

public record RegisterResponse(
        UUID userId,
        String username,
        String firstName,
        String lastName,
        RoleType role
) {
    public static class Builder {
        private UUID userId;
        private  String username;
        private  String firstName;
        private  String lastName;
        private RoleType role;

        public Builder userId(UUID userId) {
            this.userId = userId;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder role(RoleType role) {
            this.role = role;
            return this;
        }

        public RegisterResponse build() {
            return new RegisterResponse(userId, username, firstName, lastName, role);
        }
    }
}