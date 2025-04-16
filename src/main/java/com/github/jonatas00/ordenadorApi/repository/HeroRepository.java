package com.github.jonatas00.ordenadorApi.repository;

import com.github.jonatas00.ordenadorApi.entities.HeroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HeroRepository extends JpaRepository<HeroModel, UUID> {
  List<HeroModel> findAllByOrderByRankAsc();
}
