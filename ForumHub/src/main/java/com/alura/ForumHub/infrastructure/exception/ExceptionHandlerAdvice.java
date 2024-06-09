package com.alura.ForumHub.infrastructure.exception;

import java.nio.file.AccessDeniedException;
import java.time.ZonedDateTime;
import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import lombok.Builder;

/**
 * ExceptionHandler
 */
@RestControllerAdvice
public class ExceptionHandlerAdvice {

        @Builder
        private record ErrorDTO(
                        String type,
                        String title,
                        int code,
                        String path,
                        String message,
                        ZonedDateTime timestamp) {
        }

        private record ValidationFieldErrorDTO(String field, String message) {
                public ValidationFieldErrorDTO(FieldError error) {
                        this(error.getField(), error.getDefaultMessage());
                }
        }

        @ExceptionHandler({
                        EntityNotFoundException.class,
                        IllegalArgumentException.class
        })
        public ResponseEntity<ErrorDTO> handleError404(EntityNotFoundException ex) {
                return ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .body(ErrorDTO.builder()
                                                .type("https://httpstatuses.com/404")
                                                .title(HttpStatus.NOT_FOUND.getReasonPhrase())
                                                .code(HttpStatus.NOT_FOUND.value())
                                                .path("/404")
                                                .message(ex.getMessage())
                                                .timestamp(ZonedDateTime.now())
                                                .build());
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<List<ValidationFieldErrorDTO>> handlerError400(MethodArgumentNotValidException ex) {
                var errors = ex.getFieldErrors();
                return ResponseEntity.badRequest()
                                .body(errors
                                                .stream()
                                                .map(ValidationFieldErrorDTO::new)
                                                .toList());
        }

        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<ErrorDTO> handlerError400(HttpMessageNotReadableException ex) {
                return ResponseEntity.badRequest()
                                .body(ErrorDTO.builder()
                                                .type("https://httpstatuses.com/400")
                                                .title(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                                .code(HttpStatus.BAD_REQUEST.value())
                                                .path("/400")
                                                .message(ex.getMessage())
                                                .timestamp(ZonedDateTime.now())
                                                .build());
        }

        @ExceptionHandler(ValidationException.class)
        public ResponseEntity<ErrorDTO> handlerBussinessException(ValidationException ex) {
                return ResponseEntity.badRequest()
                                .body(ErrorDTO.builder()
                                                .type("https://httpstatuses.com/400")
                                                .title(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                                .code(HttpStatus.BAD_REQUEST.value())
                                                .path("/400")
                                                .message(ex.getMessage())
                                                .timestamp(ZonedDateTime.now())
                                                .build());
        }

        @ExceptionHandler(BadCredentialsException.class)
        public ResponseEntity<ErrorDTO> handlerBadCredentials() {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                ErrorDTO.builder()
                                                .type("https://httpstatuses.com/400")
                                                .title(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                                .code(HttpStatus.BAD_REQUEST.value())
                                                .path("/400")
                                                .message("Bad credentials")
                                                .timestamp(ZonedDateTime.now())
                                                .build());
        }

        @ExceptionHandler(AuthenticationException.class)
        public ResponseEntity<ErrorDTO> handlerErrorAuthentication() {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                                ErrorDTO.builder()
                                                .type("https://httpstatuses.com/401")
                                                .title(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                                                .code(HttpStatus.UNAUTHORIZED.value())
                                                .path("/401")
                                                .message("Authentication error")
                                                .timestamp(ZonedDateTime.now())
                                                .build());
        }

        @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity<ErrorDTO> handlerError403() {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                                ErrorDTO.builder()
                                                .type("https://httpstatuses.com/403")
                                                .title(HttpStatus.FORBIDDEN.getReasonPhrase())
                                                .code(HttpStatus.FORBIDDEN.value())
                                                .path("/403")
                                                .message("Access denied")
                                                .timestamp(ZonedDateTime.now())
                                                .build());
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorDTO> handlerError500(Exception ex) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                                ErrorDTO.builder()
                                                .type("https://httpstatuses.com/500")
                                                .title(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                                                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                                .path("/500")
                                                .message(ex.getMessage())
                                                .timestamp(ZonedDateTime.now())
                                                .build());
        }

}
