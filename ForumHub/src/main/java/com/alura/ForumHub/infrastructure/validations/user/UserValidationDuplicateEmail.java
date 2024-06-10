package com.alura.ForumHub.infrastructure.validations.user;

import com.alura.ForumHub.domain.entities.User;
import com.alura.ForumHub.domain.repositories.UserRepository;
import com.alura.ForumHub.infrastructure.exception.ValidationException;

/**
 * UserValidationDuplicateEmail
 */
public class UserValidationDuplicateEmail implements UserValidation {

  private final UserRepository userRepository;

  public UserValidationDuplicateEmail(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void validate(User user) {
    if (userRepository.existsByEmailAndId(user.getEmail(), user.getId())) {
      throw new ValidationException("Email already in use");
    }
  }

}
