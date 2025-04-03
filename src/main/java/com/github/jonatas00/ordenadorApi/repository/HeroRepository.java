package com.github.jonatas00.ordenadorApi.repository;

import com.github.jonatas00.ordenadorApi.models.HeroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroRepository extends JpaRepository<HeroModel, Long> {
  List<HeroModel> findAllByOrderByRankAsc();
}
