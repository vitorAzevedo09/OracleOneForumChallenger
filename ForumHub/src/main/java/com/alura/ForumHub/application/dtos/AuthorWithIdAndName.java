package com.alura.ForumHub.application.dtos;

import com.alura.ForumHub.domain.entities.User;

/**
 * AuthorWithIdAndName
 */
public record AuthorWithIdAndName(
    Long id,
    String name) {

  public AuthorWithIdAndName(User author) {
    this(author.getId(), author.getName());
  }
}
