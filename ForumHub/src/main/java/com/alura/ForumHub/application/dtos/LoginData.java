package com.alura.ForumHub.application.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * AuthenticationData
 */
public record LoginData(
        @NotNull @NotBlank String email,
        @NotNull @NotBlank String password) {
}
