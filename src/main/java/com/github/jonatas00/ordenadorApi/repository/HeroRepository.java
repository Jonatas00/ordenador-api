package com.github.jonatas00.ordenadorApi.repository;

import com.github.jonatas00.ordenadorApi.entity.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {
  List<Hero> findAllByOrderByRankAsc();
}
