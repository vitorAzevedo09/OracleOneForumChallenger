package com.alura.ForumHub.application.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.ForumHub.application.dtos.LoginData;
import com.alura.ForumHub.application.dtos.RegisterData;
import com.alura.ForumHub.application.dtos.TokenRefresh;
import com.alura.ForumHub.application.dtos.TokenResponse;
import com.alura.ForumHub.application.dtos.UserOnlyIdAndName;
import com.alura.ForumHub.domain.entities.User;
import com.alura.ForumHub.domain.services.TokenService;
import com.alura.ForumHub.domain.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

  @GetMapping("/me")
  @Operation(security = @SecurityRequirement(name = "bearerAuth"))
  public ResponseEntity<UserOnlyIdAndName> me() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) authentication.getPrincipal();
    return ResponseEntity.ok(new UserOnlyIdAndName(user));
  }

  @PostMapping("/register")
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

  @PostMapping("/login")
  public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginData data) {
    var authenticate = new UsernamePasswordAuthenticationToken(
        data.email(),
        data.password());
    var authenticated = authenticationManager.authenticate(authenticate);
    User userAuthenticaded = (User) authenticated.getPrincipal();
    String token = tokenService.generateToken(userAuthenticaded);
    String refreshToken = tokenService.generateRefreshToken(userAuthenticaded);
    return ResponseEntity.ok(new TokenResponse(token, refreshToken, tokenService.TOKEN_TYPE));
  }

  @PostMapping("/logout")
  @Operation(security = @SecurityRequirement(name = "bearerAuth"))
  public ResponseEntity<Void> logout() {
    SecurityContextHolder.clearContext();
    return ResponseEntity.ok().build();
  }

  @PostMapping("/refresh")
  @Operation(security = @SecurityRequirement(name = "bearerAuth"))
  public ResponseEntity<TokenResponse> refresh(@Valid @RequestBody TokenRefresh refreshToken) {
    if (!tokenService.validateRefreshToken(refreshToken.refreshToken())) {
      return ResponseEntity.badRequest().build();
    }
    String username = tokenService.extractEmail(refreshToken.refreshToken());
    User user = (User) userService.loadUserByUsername(username);
    var newAccessToken = tokenService.generateToken(user);
    return ResponseEntity.ok(new TokenResponse(newAccessToken, refreshToken.refreshToken(), tokenService.TOKEN_TYPE));
  }

}
