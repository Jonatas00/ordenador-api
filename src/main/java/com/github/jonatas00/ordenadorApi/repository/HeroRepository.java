package com.github.jonatas00.ordenadorApi.repository;

import com.github.jonatas00.ordenadorApi.entity.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {
  @Query("SELECT hero FROM tb_hero hero ORDER BY " +
      "(hero.hp + hero.attack + hero.defense + hero.focus + hero.special) DESC, " +
      "hero.soultrait DESC")
  List<Hero> RankHeroes();
}
