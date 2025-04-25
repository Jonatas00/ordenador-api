package com.github.jonatas00.ordenadorApi.exception.customExceptions;

public class HeroNotFoundException extends RuntimeException {

  public HeroNotFoundException(String message) {
    super(message);
  }
}
