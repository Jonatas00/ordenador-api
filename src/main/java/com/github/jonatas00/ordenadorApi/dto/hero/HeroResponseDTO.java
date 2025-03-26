package com.github.jonatas00.ordenadorApi.dto.hero;

public record HeroResponseDTO(
    Integer rank,
    Long id,
    String name,
    Integer hp,
    Integer attack,
    Integer defense,
    Integer focus,
    Integer special,
    Double soultrait
) {
}
