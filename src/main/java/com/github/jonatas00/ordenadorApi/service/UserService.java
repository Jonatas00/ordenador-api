package com.github.jonatas00.ordenadorApi.service;

import com.github.jonatas00.ordenadorApi.dto.user.UserRequestDTO;
import com.github.jonatas00.ordenadorApi.entities.RoleModel;
import com.github.jonatas00.ordenadorApi.entities.UserModel;
import com.github.jonatas00.ordenadorApi.enums.RoleName;
import com.github.jonatas00.ordenadorApi.exception.customExceptions.UserAlreadyExistsException;
import com.github.jonatas00.ordenadorApi.repository.RoleRepository;
import com.github.jonatas00.ordenadorApi.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final RoleRepository roleRepository;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.roleRepository = roleRepository;
  }

  public UserModel createUser(UserRequestDTO userDTO) {

    if (userRepository.findByUsername(userDTO.username()).isPresent()) {
      throw new UserAlreadyExistsException("Username is already exists");
    }

    RoleModel role = roleRepository.findByName(RoleName.ROLE_USER);

    UserModel user = new UserModel();
    user.setUsername(userDTO.username());
    user.setPassword(passwordEncoder.encode(userDTO.password()));

    user.setRoles(Set.of(role));

    return userRepository.save(user);
  }
}
