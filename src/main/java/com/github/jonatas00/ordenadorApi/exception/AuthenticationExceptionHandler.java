package com.github.jonatas00.ordenadorApi.exception;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.github.jonatas00.ordenadorApi.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthenticationExceptionHandler {

  @ExceptionHandler(JWTCreationException.class)
  public ResponseEntity<ApiResponse<String>> handleJWTCreationException(JWTCreationException ex) {
    return ResponseEntity
      .status(HttpStatus.UNAUTHORIZED)
      .body(new ApiResponse<>(HttpStatus.UNAUTHORIZED.value(), ex.getMessage()));
  }
}
