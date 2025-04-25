package com.github.jonatas00.ordenadorApi.dto.user;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(
  @NotBlank
  String username,
  @NotBlank
  String password) {
}
