package com.github.jonatas00.ordenadorApi.dto.mapper;

import com.github.jonatas00.ordenadorApi.dto.HeroDTO;
import com.github.jonatas00.ordenadorApi.entity.Hero;

public class HeroMapper {

  public static HeroDTO toDTO(Hero hero) {
    return new HeroDTO(
        hero.getName(),
        hero.getHp(),
        hero.getAttack(),
        hero.getDefense(),
        hero.getFocus(),
        hero.getSpecial(),
        hero.getSoultrait()
    );
  }

  public static Hero toEntity(HeroDTO heroDTO) {
    Hero hero = new Hero();
    hero.setName(heroDTO.name());
    hero.setHp(heroDTO.hp());
    hero.setAttack(heroDTO.attack());
    hero.setDefense(heroDTO.defense());
    hero.setFocus(heroDTO.focus());
    hero.setSpecial(heroDTO.special());
    hero.setSoultrait(heroDTO.soultrait());
    return hero;
  }
}
