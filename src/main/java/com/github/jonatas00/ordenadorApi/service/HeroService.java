package com.github.jonatas00.ordenadorApi.service;

import com.github.jonatas00.ordenadorApi.dto.hero.HeroMapper;
import com.github.jonatas00.ordenadorApi.dto.hero.HeroRequestDTO;
import com.github.jonatas00.ordenadorApi.dto.hero.HeroResponseDTO;
import com.github.jonatas00.ordenadorApi.entity.Hero;
import com.github.jonatas00.ordenadorApi.repository.HeroRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroService {
  private final HeroRepository heroRepository;

  private static final Logger logger = LoggerFactory.getLogger(HeroService.class);

  HeroService(HeroRepository heroRepository) {
    this.heroRepository = heroRepository;
  }

  @Transactional
  public HeroResponseDTO createHero(HeroRequestDTO dto) {
    Hero hero = HeroMapper.toEntity(dto);

    heroRepository.save(hero);

    updateHeroRanks();

    return HeroMapper.toResponseDTO(hero);
  }

  @Transactional
  public HeroResponseDTO updateHero(Long id, HeroRequestDTO dto) {
    Hero existingHero = heroRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Hero not found with id " + id));

    BeanUtils.copyProperties(dto, existingHero);

    heroRepository.save(existingHero);

    updateHeroRanks();

    return HeroMapper.toResponseDTO(existingHero);
  }

  public void deleteHero(Long id) {
    Hero hero = heroRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Hero not found with id " + id));
    heroRepository.delete(hero);

    updateHeroRanks();
  }

  public List<HeroResponseDTO> getRankedHeroes() {
    return heroRepository.findAllByOrderByRankAsc()
        .stream().map(HeroMapper::toResponseDTO).toList();
  }

  private void updateHeroRanks() {
    List<Hero> heroes = heroRepository.findAll();
    heroes.sort(this::duel);
    for (int i = 0; i < heroes.size(); i++) {
      Hero hero = heroes.get(i);
      hero.setRank(i + 1);
      heroRepository.save(hero);
      logger.info("Updated {} to rank {}", hero.getName(), hero.getRank());
    }
  }

  public int duel(Hero hero1, Hero hero2) {
    int hero1Points = 0;
    int hero2Points = 0;

    if (hero1.getHp() > hero2.getHp()) hero1Points++;
    else if (hero1.getHp() < hero2.getHp()) hero2Points++;

    if (hero1.getAttack() > hero2.getAttack()) hero1Points++;
    else if (hero1.getAttack() < hero2.getAttack()) hero2Points++;

    if (hero1.getDefense() > hero2.getDefense()) hero1Points++;
    else if (hero1.getDefense() < hero2.getDefense()) hero2Points++;

    if (hero1.getFocus() > hero2.getFocus()) hero1Points++;
    else if (hero1.getFocus() < hero2.getFocus()) hero2Points++;

    if (hero1.getSpecial() > hero2.getSpecial()) hero1Points++;
    else if (hero1.getSpecial() < hero2.getSpecial()) hero2Points++;

    if (hero1Points == hero2Points) {
      return Double.compare(hero2.getSoultrait(), hero1.getSoultrait());
    }
    return Integer.compare(hero2Points, hero1Points);
  }
}
