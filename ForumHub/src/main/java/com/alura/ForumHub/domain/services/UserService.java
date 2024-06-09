package com.alura.ForumHub.domain.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alura.ForumHub.domain.entities.User;
import com.alura.ForumHub.domain.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

/**
 * UserService
 */
@Service
public class UserService implements UserDetailsService {

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

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return repository.findByEmail(email);
  }

  @Transactional
  public User save(User user) {
    if (repository.existsByEmail(user.getEmail())) {
      throw new EntityNotFoundException("Email already in use");
    }
    return repository.save(user);
  }

}
