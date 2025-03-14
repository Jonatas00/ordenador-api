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
    if (heroes.isEmpty()) return;

    // First sort by primary criteria: duel (wins from attributes) and then soultrait
    heroes.sort((h1, h2) -> {
      int primary = duel(h1, h2);
      if (primary != 0) {
        return primary;
      }
      // If primary duel is tied, compare soultrait directly
      return Double.compare(h2.getSoultrait(), h1.getSoultrait());
    });

    // Now, for groups that remain tied (i.e. same duel result and soultrait), apply secondary and tertiary tie-breakers.
    for (int i = 0; i < heroes.size(); ) {
      int j = i + 1;
      // Find the tie group: heroes that are tied with hero at index i.
      while (j < heroes.size() && isTied(heroes.get(i), heroes.get(j))) {
        j++;
      }
      if (j - i > 1 && i > 0) { // More than one hero tied and there's a reference hero above.
        Hero reference = heroes.get(i - 1); // The hero immediately above the tie group.
        List<Hero> tieGroup = heroes.subList(i, j);
        tieGroup.sort((a, b) -> {
          // Secondary criterion: Compare duel margin against the reference hero (smaller margin is better)
          int marginA = duelMargin(reference, a);
          int marginB = duelMargin(reference, b);
          if (marginA != marginB) {
            return Integer.compare(marginA, marginB);
          }
          // Tertiary criterion: Total score = (hp + attack + defense + focus + special) * soultrait (higher wins)
          double scoreA = totalScore(a);
          double scoreB = totalScore(b);
          return Double.compare(scoreB, scoreA);
        });
      }
      i = j;
    }

    // Reassign ranks sequentially
    for (int k = 0; k < heroes.size(); k++) {
      Hero hero = heroes.get(k);
      hero.setRank(k + 1);
      heroRepository.save(hero);
      logger.info("Updated {} to rank {}", hero.getName(), hero.getRank());
    }
  }

  // Primary duel: Compare five attributes. Returns a negative value if h1 should rank higher.
  // Here, we count wins in each attribute.
  private int duel(Hero h1, Hero h2) {
    int h1Wins = 0;
    int h2Wins = 0;

    if (h1.getHp() > h2.getHp()) h1Wins++;
    else if (h1.getHp() < h2.getHp()) h2Wins++;

    if (h1.getAttack() > h2.getAttack()) h1Wins++;
    else if (h1.getAttack() < h2.getAttack()) h2Wins++;

    if (h1.getDefense() > h2.getDefense()) h1Wins++;
    else if (h1.getDefense() < h2.getDefense()) h2Wins++;

    if (h1.getFocus() > h2.getFocus()) h1Wins++;
    else if (h1.getFocus() < h2.getFocus()) h2Wins++;

    if (h1.getSpecial() > h2.getSpecial()) h1Wins++;
    else if (h1.getSpecial() < h2.getSpecial()) h2Wins++;

    // If tied in wins, this function returns 0.
    if (h1Wins == h2Wins) {
      return 0;
    }
    // We want the hero with more wins to come first (i.e. lower rank value)
    return Integer.compare(h2Wins, h1Wins);
  }

  // Checks if two heroes are tied in primary duel and have equal soultrait.
  private boolean isTied(Hero a, Hero b) {
    return duel(a, b) == 0 && Double.compare(a.getSoultrait(), b.getSoultrait()) == 0;
  }

  // Secondary criterion: Compute the duel margin between a reference hero and a given hero.
  // The margin is the absolute difference in wins when dueling the reference.
  private int duelMargin(Hero reference, Hero hero) {
    int refWins = 0;
    int heroWins = 0;

    if (reference.getHp() > hero.getHp()) refWins++;
    else if (reference.getHp() < hero.getHp()) heroWins++;

    if (reference.getAttack() > hero.getAttack()) refWins++;
    else if (reference.getAttack() < hero.getAttack()) heroWins++;

    if (reference.getDefense() > hero.getDefense()) refWins++;
    else if (reference.getDefense() < hero.getDefense()) heroWins++;

    if (reference.getFocus() > hero.getFocus()) refWins++;
    else if (reference.getFocus() < hero.getFocus()) heroWins++;

    if (reference.getSpecial() > hero.getSpecial()) refWins++;
    else if (reference.getSpecial() < hero.getSpecial()) heroWins++;

    return Math.abs(refWins - heroWins);
  }

  // Tertiary criterion: Total score = sum of all five attributes * soultrait.
  private double totalScore(Hero hero) {
    return (hero.getHp() + hero.getAttack() + hero.getDefense() + hero.getFocus() + hero.getSpecial()) * hero.getSoultrait();
  }
}
