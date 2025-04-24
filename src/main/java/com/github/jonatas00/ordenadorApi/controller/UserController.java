package com.github.jonatas00.ordenadorApi.controller;

import com.github.jonatas00.ordenadorApi.dto.user.UserRequestDTO;
import com.github.jonatas00.ordenadorApi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

  public final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody UserRequestDTO userDTO) {
    userService.createUser(userDTO);

    return ResponseEntity.ok().body("User registered successfully");
  }

  @PostMapping("/login")
  public void login() {
    return;
  }
}
