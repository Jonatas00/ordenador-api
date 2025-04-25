package com.github.jonatas00.ordenadorApi.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(
  @NotBlank(message = "Username must not be blank")
  String username,

  @NotBlank(message = "Password must not be blank")
  String password
) {
}
