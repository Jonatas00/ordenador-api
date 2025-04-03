package com.github.jonatas00.ordenadorApi.dto.user;

import jakarta.persistence.Column;

public record UserLoginRequestDTO(
  @Column(nullable = false, unique = true)
  String username,
  @Column(nullable = false)
  String password) {
}
