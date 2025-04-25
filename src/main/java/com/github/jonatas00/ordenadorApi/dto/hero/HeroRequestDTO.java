package com.github.jonatas00.ordenadorApi.dto.hero;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record HeroRequestDTO(
  @NotBlank(message = "Name cannot be null")
  String name,
  @NotBlank(message = "HP cannot be null")
  @Min(value = 0, message = "HP must be at least 0")
  int hp,
  @NotBlank(message = "Attack cannot be null")
  @Min(value = 0, message = "Attack must be at least 0")
  int attack,
  @NotBlank(message = "Defense cannot be null")
  @Min(value = 0, message = "Defense must be at least 0")
  int defense,
  @NotBlank(message = "Focus cannot be null")
  @Min(value = 0, message = "Focus must be at least 0")
  int focus,
  @NotBlank(message = "Special cannot be null")
  @Min(value = 0, message = "Special must be at least 0")
  int special,
  @NotBlank(message = "Soultrait cannot be null")
  @DecimalMin(value = "0.0", message = "Soultrait must be at least 0")
  double soultrait
) {
}
