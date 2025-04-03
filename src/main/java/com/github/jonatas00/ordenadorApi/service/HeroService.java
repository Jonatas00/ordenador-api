package com.github.jonatas00.ordenadorApi.service;

import com.github.jonatas00.ordenadorApi.dto.hero.HeroMapper;
import com.github.jonatas00.ordenadorApi.dto.hero.HeroRequestDTO;
import com.github.jonatas00.ordenadorApi.dto.hero.HeroResponseDTO;
import com.github.jonatas00.ordenadorApi.models.HeroModel;
import com.github.jonatas00.ordenadorApi.repository.HeroRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HeroService {
  private static final Logger logger = LoggerFactory.getLogger(HeroService.class);
  private final HeroRepository heroRepository;

  HeroService(HeroRepository heroRepository) {
    this.heroRepository = heroRepository;
  }

  @Transactional
  public HeroResponseDTO createHero(HeroRequestDTO dto) {
    HeroModel hero = HeroMapper.toEntity(dto);

    heroRepository.save(hero);

    updateHeroRanks();

    return HeroMapper.toResponseDTO(hero);
  }

  @Transactional
  public HeroResponseDTO updateHero(UUID id, HeroRequestDTO dto) {
    HeroModel existingHero = heroRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Hero not found with id " + id));

    BeanUtils.copyProperties(dto, existingHero);

    heroRepository.save(existingHero);

    updateHeroRanks();

    return HeroMapper.toResponseDTO(existingHero);
  }

  @Transactional
  public void deleteHero(UUID id) {
    HeroModel hero = heroRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Hero not found with id " + id));
    heroRepository.delete(hero);

    updateHeroRanks();
  }

  public HeroResponseDTO getHeroById(UUID id) {
    HeroModel hero = heroRepository.findById(id).orElseThrow(() -> new RuntimeException("Hero not found with id " + id));

    return HeroMapper.toResponseDTO(hero);
  }

  public List<HeroResponseDTO> getRankedHeroes() {
    return heroRepository.findAllByOrderByRankAsc()
      .stream().map(HeroMapper::toResponseDTO).toList();
  }


  private void updateHeroRanks() {
    List<HeroModel> heroes = heroRepository.findAll();
    if (heroes.isEmpty()) return;

    heroes.sort((h1, h2) -> {
      int primary = duel(h1, h2);
      if (primary != 0) return primary;

      int soultraitComparison = Double.compare(h2.getSoultrait(), h1.getSoultrait());
      if (soultraitComparison != 0) return soultraitComparison;

      return Double.compare(totalScore(h2), totalScore(h1));
    });

    for (int i = 0; i < heroes.size(); ) {
      int j = i + 1;
      while (j < heroes.size() && isTied(heroes.get(i), heroes.get(j))) j++;

      if (j - i > 1 && i > 0) {
        HeroModel reference = heroes.get(i - 1);
        List<HeroModel> tieGroup = new ArrayList<>(heroes.subList(i, j));
        tieGroup.sort((a, b) -> {
          int marginA = duelMargin(reference, a);
          int marginB = duelMargin(reference, b);
          if (marginA != marginB) return Integer.compare(marginA, marginB);

          return Double.compare(totalScore(b), totalScore(a));
        });
        for (int k = 0; k < tieGroup.size(); k++) {
          heroes.set(i + k, tieGroup.get(k));
        }
      }
      i = j;
    }

    for (int k = 0; k < heroes.size(); k++) {
      heroes.get(k).setRank(k + 1);
    }
    heroRepository.saveAll(heroes);
    logger.info("Updated {} heroes' ranks", heroes.size());
  }

  private int duel(HeroModel h1, HeroModel h2) {
    return compareAttributes(h1, h2, false);
  }

  private int duelMargin(HeroModel reference, HeroModel hero) {
    return Math.abs(compareAttributes(reference, hero, true));
  }

  private int compareAttributes(HeroModel h1, HeroModel h2, boolean absolute) {
    int h1Wins = 0, h2Wins = 0;
    int[] statsH1 = {h1.getHp(), h1.getAttack(), h1.getDefense(), h1.getFocus(), h1.getSpecial()};
    int[] statsH2 = {h2.getHp(), h2.getAttack(), h2.getDefense(), h2.getFocus(), h2.getSpecial()};

    for (int i = 0; i < statsH1.length; i++) {
      if (statsH1[i] > statsH2[i]) h1Wins++;
      else if (statsH1[i] < statsH2[i]) h2Wins++;
    }
    return absolute ? h1Wins - h2Wins : Integer.compare(h2Wins, h1Wins);
  }

  private boolean isTied(HeroModel a, HeroModel b) {
    return duel(a, b) == 0 && Double.compare(a.getSoultrait(), b.getSoultrait()) == 0 && Double.compare(totalScore(a), totalScore(b)) == 0;
  }

  private double totalScore(HeroModel hero) {
    return (hero.getHp() + hero.getAttack() + hero.getDefense() + hero.getFocus() + hero.getSpecial()) * hero.getSoultrait();
  }

}
