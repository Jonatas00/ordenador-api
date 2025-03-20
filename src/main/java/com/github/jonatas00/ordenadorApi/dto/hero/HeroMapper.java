package com.github.jonatas00.ordenadorApi.dto.hero;

import com.github.jonatas00.ordenadorApi.entity.Hero;

public class HeroMapper {

  public static Hero toEntity(HeroRequestDTO dto) {
    return new Hero(
        dto.name(),
        dto.hp(),
        dto.attack(),
        dto.defense(),
        dto.focus(),
        dto.special(),
        dto.soultrait());
  }

  public static HeroResponseDTO toResponseDTO(Hero hero) {
    return new HeroResponseDTO(
        hero.getId(),
        hero.getRank(),
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
