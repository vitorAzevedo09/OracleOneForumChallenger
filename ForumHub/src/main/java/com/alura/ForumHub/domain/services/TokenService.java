package com.alura.ForumHub.domain.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alura.ForumHub.domain.entities.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

@Service
public class TokenService {

  @Value("${api.security.token.secret}")
  private String secret;

  public String TOKEN_TYPE = "Bearer";

  public String generateToken(User user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      return JWT.create()
          .withIssuer("forumhub")
          .withSubject(user.getEmail())
          .withExpiresAt(generateExpirationDate())
          .sign(algorithm);
    } catch (Exception e) {
      throw new RuntimeException("Error while generating JWT token", e);
    }

  }

  public String generateRefreshToken(User user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      return JWT.create()
          .withIssuer("forumhub")
          .withSubject(user.getEmail())
          .withExpiresAt(generateExpirationDate())
          .sign(algorithm);
    } catch (Exception e) {
      throw new RuntimeException("Error while generating JWT token", e);
    }
  }

  public Boolean validateToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      JWT
          .require(algorithm)
          .withIssuer("forumhub")
          .build()
          .verify(token)
          .getSubject();
      return true;
    } catch (Exception e) {
      return false;
    }

  }

  public Boolean validateRefreshToken(String refreshToken) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      JWT
          .require(algorithm)
          .withIssuer("forumhub")
          .build()
          .verify(refreshToken)
          .getSubject();
      return true;
    } catch (JWTCreationException e) {
      return false;
    }
  }

  public String extractEmail(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      return JWT
          .require(algorithm)
          .withIssuer("forumhub")
          .build()
          .verify(token)
          .getSubject();
    } catch (JWTCreationException e) {
      return null;
    }
  }

  private Instant generateExpirationDate() {
    return LocalDateTime.now()
        .plusHours(2)
        .toInstant(ZoneOffset.of("-03:00"));
  }
}
