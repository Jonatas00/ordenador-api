package com.github.jonatas00.ordenadorApi.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity(name = "tb_user")
public class UserModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;

  @ManyToMany(fetch = FetchType.EAGER, targetEntity = RoleModel.class)
  @JoinTable(
    name = "tb_user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private Set<RoleModel> roles;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<RoleModel> getRoles() {
    return roles;
  }

  public void setRoles(Set<RoleModel> roles) {
    this.roles = roles;
  }
}
