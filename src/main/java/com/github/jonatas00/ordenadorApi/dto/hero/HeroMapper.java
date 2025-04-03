package com.github.jonatas00.ordenadorApi.dto.hero;

import com.github.jonatas00.ordenadorApi.models.HeroModel;

public class HeroMapper {

  public static HeroModel toEntity(HeroRequestDTO dto) {
    return new HeroModel(
      dto.name(),
      dto.hp(),
      dto.attack(),
      dto.defense(),
      dto.focus(),
      dto.special(),
      dto.soultrait());
  }

  public static HeroResponseDTO toResponseDTO(HeroModel hero) {
    return new HeroResponseDTO(
      hero.getRank(),
      hero.getId(),
      hero.getName(),
      hero.getHp(),
      hero.getAttack(),
      hero.getDefense(),
      hero.getFocus(),
      hero.getSpecial(),
      hero.getSoultrait()
    );
  }
}
