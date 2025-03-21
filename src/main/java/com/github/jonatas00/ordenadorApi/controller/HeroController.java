package com.github.jonatas00.ordenadorApi.controller;

import com.github.jonatas00.ordenadorApi.dto.hero.HeroRequestDTO;
import com.github.jonatas00.ordenadorApi.dto.hero.HeroResponseDTO;
import com.github.jonatas00.ordenadorApi.service.HeroService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("character")
public class HeroController {
  private final HeroService heroService;

  HeroController(HeroService heroService) {
    this.heroService = heroService;
  }

  @PostMapping
  public ResponseEntity<HeroResponseDTO> registerCharacter(@Valid @RequestBody HeroRequestDTO dto) {
    HeroResponseDTO createdHero = heroService.createHero(dto);
    return ResponseEntity.status(200).body(createdHero);
  }

  @GetMapping
  public ResponseEntity<List<HeroResponseDTO>> listHeroByRank() {
    List<HeroResponseDTO> heroes = heroService.getRankedHeroes();
    return ResponseEntity.status(200).body(heroes);
  }

  @GetMapping("{id}")
  public ResponseEntity<HeroResponseDTO> getHeroById(@PathVariable Long id) {
    HeroResponseDTO hero = heroService.getHeroById(id);

    return ResponseEntity.status(200).body(hero);
  }

  @PutMapping("{id}")
  public ResponseEntity<HeroResponseDTO> updateHero(@PathVariable("id") Long id, @Valid @RequestBody HeroRequestDTO dto) {
    HeroResponseDTO updateHero = heroService.updateHero(id, dto);

    return ResponseEntity.status(200).body(updateHero);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteHero(@PathVariable Long id) {
    heroService.deleteHero(id);

    return ResponseEntity.status(200).body("Character removed");
  }
}
