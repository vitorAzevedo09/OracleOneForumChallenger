package com.alura.ForumHub.application.dtos;

import com.alura.ForumHub.domain.entities.User;

/**
 * UserOnlyIdAndName
 */
public record UserOnlyIdAndName(Long id, String name) {

  public UserOnlyIdAndName(User user) {
    this(user.getId(), user.getName());
  }
}
