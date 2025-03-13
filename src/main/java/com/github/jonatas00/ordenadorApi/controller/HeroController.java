package com.github.jonatas00.ordenadorApi.controller;

import com.github.jonatas00.ordenadorApi.dto.HeroDTO;
import com.github.jonatas00.ordenadorApi.entity.Hero;
import com.github.jonatas00.ordenadorApi.services.HeroService;
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
  public ResponseEntity<HeroDTO> registerCharacter(@Valid @RequestBody HeroDTO dto) {
    HeroDTO createdHero = heroService.registerCharacter(dto);
    return ResponseEntity.status(200).body(createdHero);
  }

  @GetMapping
  public ResponseEntity<List<Hero>> listHeroes() {
    List<Hero> heroes = heroService.listHeroes();

    return ResponseEntity.status(200).body(heroes);
  }


  @GetMapping("rank")
  public ResponseEntity<List<Hero>> listHeroByRank() {
    List<Hero> heroes = heroService.listHeroByRank();
    return ResponseEntity.status(200).body(heroes);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteHero(@PathVariable Long id) {
    heroService.deleteHeroById(id);

    return ResponseEntity.status(200).body("Character removed");
  }
}
