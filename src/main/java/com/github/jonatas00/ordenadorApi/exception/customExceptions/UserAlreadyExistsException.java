package com.github.jonatas00.ordenadorApi.exception.customExceptions;

public class UserAlreadyExistsException extends RuntimeException {
  public UserAlreadyExistsException(String message) {
    super(message);
  }
}
