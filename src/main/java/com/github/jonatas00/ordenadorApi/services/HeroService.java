package com.github.jonatas00.ordenadorApi.services;

import com.github.jonatas00.ordenadorApi.dto.HeroDTO;
import com.github.jonatas00.ordenadorApi.dto.mapper.HeroMapper;
import com.github.jonatas00.ordenadorApi.entity.Hero;
import com.github.jonatas00.ordenadorApi.repository.HeroRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroService {
  private final HeroRepository heroRepository;

  HeroService(HeroRepository heroRepository) {
    this.heroRepository = heroRepository;
  }

  public HeroDTO registerCharacter(HeroDTO dto) {
    Hero hero = HeroMapper.toEntity(dto);

    heroRepository.save(hero);

    return dto;
  }

  public Hero getHeroById(long id) {
    return heroRepository.findById(id).orElseThrow();
  }

  public List<Hero> listHeroes() {
    return heroRepository.findAll();
  }

  public List<Hero> listHeroByRank() {
    return heroRepository.RankHeroes();
  }

  public Hero updateHero(Long id, Hero updatedHero) {
    // Fetch the existing hero; if not found, throw an exception (or handle as needed)
    Hero existingHero = heroRepository.findById(id).orElseThrow(() -> new RuntimeException("Hero not found with id: " + id));

    // Copy properties from the updatedHero to the existingHero.
    // The "id" property is excluded to keep the existing identifier intact.
    BeanUtils.copyProperties(updatedHero, existingHero, "id");

    // Save the updated entity to the database
    return heroRepository.save(existingHero);
  }

  public void deleteHeroById(Long id) {
    heroRepository.deleteById(id);
  }
}
