
package com.alura.ForumHub.application.dtos;

import com.alura.ForumHub.domain.entities.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * UserWithoutId
 */
public record UserWithId(
    @NotNull @NotBlank String name,
    @NotNull @NotBlank String email) {

  public UserWithId(User user) {
    this(user.getName(), user.getEmail());
  }
}
