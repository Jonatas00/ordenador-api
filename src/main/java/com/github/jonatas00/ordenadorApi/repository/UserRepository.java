package com.github.jonatas00.ordenadorApi.repository;

import com.github.jonatas00.ordenadorApi.entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
  Optional<UserModel> findByUsername(String username);
}
