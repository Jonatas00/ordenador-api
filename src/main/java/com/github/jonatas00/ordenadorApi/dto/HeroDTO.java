package com.github.jonatas00.ordenadorApi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record HeroDTO(
    @NotEmpty(message = "The hero's name cannot be empty.")
    String name,

    @NotNull(message = "HP is required.")
    @Min(value = 0, message = "HP must be at least 0.")
    Integer hp,

    @NotNull(message = "Attack is required.")
    @Min(value = 0, message = "Attack must be at least 0.")
    Integer attack,

    @NotNull(message = "Defense is required.")
    @Min(value = 0, message = "Defense must be at least 0.")
    Integer defense,

    @NotNull(message = "Focus is required.")
    @Min(value = 0, message = "Focus must be at least 0.")
    Integer focus,

    @NotNull(message = "Special is required.")
    @Min(value = 0, message = "Special must be at least 0.")
    Integer special,

    @NotNull(message = "Soultrait is required.")
    @Min(value = 0, message = "Soultrait must be at least 0.")
    Double soultrait
) {
}
