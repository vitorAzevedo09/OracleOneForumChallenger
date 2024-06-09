package com.alura.ForumHub.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alura.ForumHub.domain.entities.User;

/**
 * UserRepository
 */
@Repository
public interface UserRepositoryJPA extends JpaRepository<User, Long> {

  boolean existsByEmail(String email);

}
