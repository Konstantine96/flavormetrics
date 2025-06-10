package com.molovataconstantin.flavormetrics.model.request;

import com.molovataconstantin.flavormetrics.model.enums.RoleType;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Register request")
public record RegisterRequest(
        @Schema(description = "Unique identifier", example = "constantin@email.com")
        String username,

        @Schema(description = "User first name", example = "Constantin")
        String firstName,

        @Schema(description = "User last name", example = "Molovata")
        String lastName,

        @Schema(description = "User credentials", example = "strongPassword")
        String password,

        @Schema(description = "User role should not be ADMIN " +
                "in case you will not be able to register", example = "ROLE_USER")
        RoleType role) {

}