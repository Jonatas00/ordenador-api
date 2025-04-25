package com.github.jonatas00.ordenadorApi.exception;

import com.github.jonatas00.ordenadorApi.dto.response.ApiError;
import com.github.jonatas00.ordenadorApi.dto.response.ApiResponse;
import com.github.jonatas00.ordenadorApi.exception.customExceptions.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<String>> handleValidationException(MethodArgumentNotValidException ex) {
    String message = ex.getBindingResult()
      .getFieldErrors()
      .stream()
      .map(error -> error.getField() + ": " + error.getDefaultMessage())
      .findFirst()
      .orElse("Dados inválidos");

    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(new ApiResponse<>(400, message));
  }

  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<ApiError> handleUserAlreadyExists(UserAlreadyExistsException ex) {
    ApiError error = new ApiError(HttpStatus.CONFLICT.value(), Collections.singletonList(ex.getMessage()));

    return new ResponseEntity<>(error, HttpStatus.CONFLICT);
  }

}
