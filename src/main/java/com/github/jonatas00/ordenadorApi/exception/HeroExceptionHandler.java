package com.github.jonatas00.ordenadorApi.exception;

import com.github.jonatas00.ordenadorApi.dto.response.ApiResponse;
import com.github.jonatas00.ordenadorApi.exception.customExceptions.HeroNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HeroExceptionHandler {

  @ExceptionHandler(HeroNotFoundException.class)
  public ResponseEntity<ApiResponse<String>> heroNotFoundException(HeroNotFoundException e) {

    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
  }
}
