package com.alura.ForumHub.domain.services;

import org.springframework.stereotype.Service;

import com.alura.ForumHub.domain.entities.User;
import com.alura.ForumHub.domain.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

/**
 * UserService
 */
@Service
public class UserService {

  private final UserRepository repository;

  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  public User findOrFail(User user) {
    return repository
        .findById(user.getId())
        .orElseThrow(() -> new EntityNotFoundException(
            String.format("Author with id %d not found", user.getId())));
  }

  @Transactional
  public User save(User user) {
    return repository.save(user);
  }

}
