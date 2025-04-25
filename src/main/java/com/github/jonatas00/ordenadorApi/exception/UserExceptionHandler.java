package com.github.jonatas00.ordenadorApi.exception;

import com.github.jonatas00.ordenadorApi.dto.response.ApiResponse;
import com.github.jonatas00.ordenadorApi.exception.customExceptions.UsernameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

  @ExceptionHandler(UsernameAlreadyExistsException.class)
  public ResponseEntity<ApiResponse<String>> userAlreadyExistsException(UsernameAlreadyExistsException e) {

    HttpStatus status = HttpStatus.CONFLICT;

    return ResponseEntity
      .status(status)
      .body(new ApiResponse<>(status.value(), e.getMessage()));
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<ApiResponse<String>> usernameNotFoundException(UsernameNotFoundException e) {
    HttpStatus status = HttpStatus.BAD_REQUEST;

    return ResponseEntity
      .status(status)
      .body(new ApiResponse<>(status.value(), e.getMessage()));
  }

}
