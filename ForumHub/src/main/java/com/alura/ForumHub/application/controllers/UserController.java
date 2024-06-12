package com.alura.ForumHub.application.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.ForumHub.application.dtos.UserOnlyIdAndName;
import com.alura.ForumHub.application.dtos.UserWithId;
import com.alura.ForumHub.application.dtos.UserWithoutId;
import com.alura.ForumHub.domain.entities.User;
import com.alura.ForumHub.domain.services.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

/**
 * UserController
 */
@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<Page<UserOnlyIdAndName>> list(Pageable pageable) {
    return ResponseEntity.ok(
        userService
            .findAll(pageable)
            .map(UserOnlyIdAndName::new));
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserOnlyIdAndName> get(@PathVariable final Long id) {
    return ResponseEntity.ok(
        new UserOnlyIdAndName(
            userService.findOrFail(id)));
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserWithId> update(
      @PathVariable final Long id,
      @Valid @RequestBody UserWithoutId user) {
    User userUpdated = userService.update(id, user.toEntity());
    UserWithId userOut = new UserWithId(userUpdated);
    return ResponseEntity.ok(userOut);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable final Long id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
