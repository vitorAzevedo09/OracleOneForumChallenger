package com.alura.ForumHub.application.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.ForumHub.application.dtos.LoginData;
import com.alura.ForumHub.application.dtos.RegisterData;
import com.alura.ForumHub.application.dtos.UserOnlyIdAndName;
import com.alura.ForumHub.application.dtos.UserWithToken;
import com.alura.ForumHub.domain.entities.User;
import com.alura.ForumHub.domain.services.TokenService;
import com.alura.ForumHub.domain.services.UserService;

import jakarta.validation.Valid;

/**
 * AuthController
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

  private final UserService userService;
  private final TokenService tokenService;
  private final AuthenticationManager authenticationManager;

  AuthController(AuthenticationManager authenticationManager, TokenService tokenService, UserService userService) {
    this.userService = userService;
    this.tokenService = tokenService;
    this.authenticationManager = authenticationManager;
  }

  @RequestMapping("/me")
  public ResponseEntity<?> me() {
    return ResponseEntity.ok().build();
  }

  @RequestMapping("/register")
  public ResponseEntity<UserOnlyIdAndName> register(@Valid @RequestBody RegisterData data) {
    String encryptedPassword = new BCryptPasswordEncoder().encode(data.password()); // data.password();
    User user = User
        .builder()
        .name(data.name())
        .email(data.email())
        .password(encryptedPassword)
        .build();
    User newUser = userService.save(user);
    return ResponseEntity.ok(new UserOnlyIdAndName(newUser));
  }

  @RequestMapping("/login")
  public ResponseEntity<UserWithToken> login(@Valid @RequestBody LoginData data) {
    var authenticate = new UsernamePasswordAuthenticationToken(
        data.email(),
        data.password());
    var authenticated = authenticationManager.authenticate(authenticate);
    User userAuthenticaded = (User) authenticated.getPrincipal();
    var token = tokenService.generateToken(userAuthenticaded);
    return ResponseEntity.ok(new UserWithToken(token, tokenService.TOKEN_TYPE, userAuthenticaded));
  }

  @RequestMapping("/logout")
  public ResponseEntity<Void> logout() {
    return ResponseEntity.ok().build();
  }

  @RequestMapping("/refresh")
  public ResponseEntity<Void> refresh() {
    return ResponseEntity.ok().build();
  }

}
