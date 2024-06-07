package com.alura.ForumHub.application.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.ForumHub.application.dtos.UserOnlyIdAndName;
import com.alura.ForumHub.application.dtos.UserWithoutId;
import com.alura.ForumHub.domain.services.UserService;

import jakarta.validation.Valid;

/**
 * UserController
 */
@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<UserOnlyIdAndName> create(@Valid @RequestBody UserWithoutId user) {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(
            new UserOnlyIdAndName(
                userService.save(user.toEntity())));
  }

}
