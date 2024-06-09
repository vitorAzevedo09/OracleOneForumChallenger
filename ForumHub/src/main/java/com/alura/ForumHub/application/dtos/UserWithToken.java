package com.alura.ForumHub.application.dtos;

import com.alura.ForumHub.domain.entities.User;

/**
 * UserWithToken
 */
public record UserWithToken(
    String token,
    String type,
    Long id,
    String username,
    String email) {

  public UserWithToken(String token, String type, User user) {
    this(
        token,
        type,
        user.getId(),
        user.getName(),
        user.getEmail());
  }
}
