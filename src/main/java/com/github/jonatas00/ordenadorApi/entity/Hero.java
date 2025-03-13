package com.github.jonatas00.ordenadorApi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "tb_hero")
public class Hero {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  String name;

  Integer hp;

  Integer attack;

  Integer defense;

  Integer focus;

  Integer special;

  Double soultrait;

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setHp(Integer hp) {
    this.hp = hp;
  }

  public Integer getHp() {
    return hp;
  }

  public void setAttack(Integer attack) {
    this.attack = attack;
  }

  public Integer getAttack() {
    return this.attack;
  }

  public void setDefense(Integer defense) {
    this.defense = defense;
  }

  public Integer getDefense() {
    return this.defense;
  }

  public void setFocus(Integer focus) {
    this.focus = focus;
  }

  public Integer getFocus() {
    return this.focus;
  }

  public void setSpecial(Integer special) {
    this.special = special;
  }

  public Integer getSpecial() {
    return this.special;
  }

  public void setSoultrait(Double soultrait) {
    this.soultrait = soultrait;
  }

  public Double getSoultrait() {
    return this.soultrait;
  }
}
