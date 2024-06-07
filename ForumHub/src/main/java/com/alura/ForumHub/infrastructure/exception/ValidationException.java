package com.alura.ForumHub.infrastructure.exception;

/**
 * ValidationException
 */
public class ValidationException extends RuntimeException {
  public ValidationException(String message) {
    super(message);
  }

  public ValidationException(String message, Throwable cause) {
    super(message, cause);
  }

}
