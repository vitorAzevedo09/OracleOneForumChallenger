package com.alura.ForumHub.infrastructure.security;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import com.alura.ForumHub.domain.services.TokenService;
import com.alura.ForumHub.domain.services.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * JWTSecurityFilter
 */
@Component
public class JWTSecurityFilter extends OncePerRequestFilter {

  private final TokenService tokenService;
  private final UserService userService;

  JWTSecurityFilter(TokenService tokenService, UserService user) {
    this.tokenService = tokenService;
    this.userService = user;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    var token = recoverToken(request);
    if (token == null) {
      filterChain.doFilter(request, response);
      return;
    }
    if (!tokenService.validateToken(token)) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
    }
    String username = tokenService.extractEmail(token);
    var user = userService.loadUserByUsername(username);
    var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authentication);
    filterChain.doFilter(request, response);
  }

  private String recoverToken(HttpServletRequest request) {
    var authHeader = request.getHeader("Authorization");
    if (authHeader == null)
      return null;
    return authHeader.replace("Bearer ", "");
  }
}
