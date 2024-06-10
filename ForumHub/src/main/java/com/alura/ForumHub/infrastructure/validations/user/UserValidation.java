package com.alura.ForumHub.infrastructure.validations.user;

import com.alura.ForumHub.domain.entities.User;

/**
 * UserValidation
 */
public interface UserValidation {

  public void validate(User user);

}
