package com.github.jonatas00.ordenadorApi.dto.hero;

import java.util.UUID;

public record HeroResponseDTO(
  Integer rank,
  UUID id,
  String name,
  Integer hp,
  Integer attack,
  Integer defense,
  Integer focus,
  Integer special,
  Double soultrait
) {
}
