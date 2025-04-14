package com.github.jonatas00.ordenadorApi.entities;

import com.github.jonatas00.ordenadorApi.enums.RoleName;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

@Entity(name = "tb_role")
public class RoleModel implements GrantedAuthority {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, unique = true)
  private RoleName name;


  @Override
  public String getAuthority() {
    return this.name.toString();
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public RoleName getName() {
    return name;
  }

  public void setName(RoleName name) {
    this.name = name;
  }
}
