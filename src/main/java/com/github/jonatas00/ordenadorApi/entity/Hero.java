package com.github.jonatas00.ordenadorApi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "tb_hero")
public class Hero {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Integer rank;
  private String name;
  private Integer hp;
  private Integer attack;
  private Integer defense;
  private Integer focus;
  private Integer special;
  private Double soultrait;

  public Hero() {
  }

  public Hero(String name, Integer hp, Integer attack, Integer defense, Integer focus, Integer special, Double soultrait) {
    this.name = name;
    this.hp = hp;
    this.attack = attack;
    this.defense = defense;
    this.focus = focus;
    this.special = special;
    this.soultrait = soultrait;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getHp() {
    return hp;
  }

  public void setHp(Integer hp) {
    this.hp = hp;
  }

  public Integer getAttack() {
    return this.attack;
  }

  public void setAttack(Integer attack) {
    this.attack = attack;
  }

  public Integer getDefense() {
    return this.defense;
  }

  public void setDefense(Integer defense) {
    this.defense = defense;
  }

  public Integer getFocus() {
    return this.focus;
  }

  public void setFocus(Integer focus) {
    this.focus = focus;
  }

  public Integer getSpecial() {
    return this.special;
  }

  public void setSpecial(Integer special) {
    this.special = special;
  }

  public Double getSoultrait() {
    return this.soultrait;
  }

  public void setSoultrait(Double soultrait) {
    this.soultrait = soultrait;
  }

  public Integer getRank() {
    return rank;
  }

  public void setRank(Integer rank) {
    this.rank = rank;
  }
}
