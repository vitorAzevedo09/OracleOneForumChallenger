package com.alura.ForumHub.application.dtos;

import com.alura.ForumHub.domain.entities.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * UserWithoutId
 */
public record UserWithoutId(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank String password) {

    public User toEntity() {
        return User.builder()
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .build();
    }
}
