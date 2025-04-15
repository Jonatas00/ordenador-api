package com.github.jonatas00.ordenadorApi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

  @PostMapping("/register")
  public void register() {
    return;
  }

  @PostMapping("/login")
  public void login() {
   return;
  }
}
