package com.alura.ForumHub.application.dtos;

import com.alura.ForumHub.domain.entities.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * UserWithoutId
 */
public record UserWithoutId(
        @NotNull @NotBlank String name,
        @NotNull @NotBlank String email) {

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .build();
    }
}
