package com.alura.ForumHub.application.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * RegisterDTO
 */
public record RegisterData(
                @NotNull @NotBlank String name,
                @NotNull @NotBlank @Email String email,
                @NotNull @NotBlank String password) {
}
