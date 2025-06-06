package com.github.jonatas00.ordenadorApi.exception;

import com.github.jonatas00.ordenadorApi.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {


  @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
  public ResponseEntity<ApiResponse<String>> unauthorizedException(Exception e) {
    HttpStatus status = HttpStatus.UNAUTHORIZED;

    return ResponseEntity
      .status(status)
      .body(new ApiResponse<>(status.value(), e.getMessage()));
  }
}
