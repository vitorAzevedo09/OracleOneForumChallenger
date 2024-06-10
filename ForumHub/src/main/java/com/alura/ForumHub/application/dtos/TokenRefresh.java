package com.alura.ForumHub.application.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * TokenRefresh
 */
public record TokenRefresh(
    @NotNull @NotBlank String refreshToken) {
}
