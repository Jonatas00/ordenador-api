package com.github.jonatas00.ordenadorApi.repository;

import com.github.jonatas00.ordenadorApi.entities.RoleModel;
import com.github.jonatas00.ordenadorApi.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Long> {
  RoleModel findByName(RoleName name);
}
