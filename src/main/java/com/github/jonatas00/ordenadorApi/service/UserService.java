package com.github.jonatas00.ordenadorApi.service;

import com.github.jonatas00.ordenadorApi.dto.user.UserRequestDTO;
import com.github.jonatas00.ordenadorApi.entities.RoleModel;
import com.github.jonatas00.ordenadorApi.entities.UserModel;
import com.github.jonatas00.ordenadorApi.enums.RoleName;
import com.github.jonatas00.ordenadorApi.exception.customExceptions.UsernameAlreadyExistsException;
import com.github.jonatas00.ordenadorApi.repository.RoleRepository;
import com.github.jonatas00.ordenadorApi.repository.UserRepository;
import com.github.jonatas00.ordenadorApi.security.authentication.JwtTokenService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final RoleRepository roleRepository;

  private final JwtTokenService jwtTokenService;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, JwtTokenService jwtTokenService) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.roleRepository = roleRepository;
    this.jwtTokenService = jwtTokenService;
  }

  public UserModel signup(UserRequestDTO userDTO) {

    if (userRepository.findByUsername(userDTO.username()).isPresent()) {
      throw new UsernameAlreadyExistsException("Username is already exists");
    }

    RoleModel role = roleRepository.findByName(RoleName.ROLE_USER);

    UserModel user = new UserModel();
    user.setUsername(userDTO.username());
    user.setPassword(passwordEncoder.encode(userDTO.password()));

    user.setRoles(Set.of(role));

    return userRepository.save(user);
  }

  public String login(UserRequestDTO userDTO) {

    UserModel user = userRepository.findByUsername(userDTO.username()).orElseThrow(
      () -> new UsernameNotFoundException("Invalid Username")
    );

    if (!user.getPassword().equals(passwordEncoder.encode(userDTO.password())) || user.getUsername() == null) {
      throw new BadCredentialsException("Invalid password");
    }


    return "";
  }
}
