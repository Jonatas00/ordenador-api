package com.github.jonatas00.ordenadorApi.controller;

import com.github.jonatas00.ordenadorApi.dto.response.ApiResponse;
import com.github.jonatas00.ordenadorApi.dto.user.UserRequestDTO;
import com.github.jonatas00.ordenadorApi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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

  @PostMapping("/signup")
  public ResponseEntity<ApiResponse<UserRequestDTO>> register(@Valid @RequestBody UserRequestDTO userDTO) {
    userService.signup(userDTO);

    return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(201, userDTO));
  }


  @PostMapping("/login")
  public void login(@Valid @RequestBody UserRequestDTO userDTO) {
    String token = userService.login(userDTO);
  }
}
