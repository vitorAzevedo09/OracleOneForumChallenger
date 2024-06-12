package com.alura.ForumHub.domain.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import com.alura.ForumHub.domain.entities.User;
import com.alura.ForumHub.domain.repositories.UserRepository;
import com.alura.ForumHub.infrastructure.validations.user.UserValidation;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

/**
 * UserService
 */
@Service
public class UserService implements UserDetailsService {

  private final UserRepository repository;
  private List<UserValidation> userValidations;

  public UserService(UserRepository repository, List<UserValidation> userValidations) {
    this.repository = repository;
    this.userValidations = userValidations;
  }

  public User findOrFail(final Long id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException(
            String.format("User with id %d not found", id)));
  }

  public Page<User> findAll(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return repository
        .findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException(email));
  }

  @Transactional
  public User save(User user) {
    userValidations.forEach(
        validation -> validation.validate(user));
    return repository.save(user);
  }

  @Transactional
  public User update(final Long id, User user) {
    User userDB = findOrFail(id);

    user.setId(userDB.getId());
    user.setPassword(userDB.getPassword());

    userValidations.forEach(
        validation -> validation.validate(user));

    return repository.save(user);
  }

  @Transactional
  public void delete(final Long id) {
    User user = findOrFail(id);
    repository.delete(user);
  }

}
