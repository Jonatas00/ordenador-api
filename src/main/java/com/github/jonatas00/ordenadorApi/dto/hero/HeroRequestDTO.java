package com.github.jonatas00.ordenadorApi.dto.hero;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record HeroRequestDTO(
  @NotNull(message = "Name cannot be null")
  String name,
  @NotNull(message = "HP cannot be null")
  @Min(value = 0, message = "HP must be at least 0")
  int hp,
  @NotNull(message = "Attack cannot be null")
  @Min(value = 0, message = "Attack must be at least 0")
  int attack,
  @NotNull(message = "Defense cannot be null")
  @Min(value = 0, message = "Defense must be at least 0")
  int defense,
  @NotNull(message = "Focus cannot be null")
  @Min(value = 0, message = "Focus must be at least 0")
  int focus,
  @NotNull(message = "Special cannot be null")
  @Min(value = 0, message = "Special must be at least 0")
  int special,
  @NotNull(message = "Soultrait cannot be null")
  @DecimalMin(value = "0.0", message = "Soultrait must be at least 0")
  double soultrait
) {
}
