package com.alura.ForumHub.domain.services;

import org.springframework.stereotype.Service;

import com.alura.ForumHub.domain.entities.User;
import com.alura.ForumHub.domain.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

/**
 * UserService
 */
@Service
public class UserService {

  private final UserRepository repository;

  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  public void existUserOrFail(User user) {
    if (!repository.existsById(user.getId())) {
      throw new EntityNotFoundException(
          String.format("Author with id %d not found", user.getId()));
    }
  }

}
