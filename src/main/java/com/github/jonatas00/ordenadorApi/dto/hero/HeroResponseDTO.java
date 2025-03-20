package com.github.jonatas00.ordenadorApi.dto.hero;

public record HeroResponseDTO(
    Long id,
    Integer rank,
    String name,
    Integer hp,
    Integer attack,
    Integer defense,
    Integer focus,
    Integer special,
    Double soultrait
) {
}
