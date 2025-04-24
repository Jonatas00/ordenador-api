package com.github.jonatas00.ordenadorApi.config;

import com.github.jonatas00.ordenadorApi.entities.RoleModel;
import com.github.jonatas00.ordenadorApi.enums.RoleName;
import com.github.jonatas00.ordenadorApi.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class RoleInitializer {

  @Bean
  CommandLineRunner initRoles(RoleRepository roleRepository) {
    return args ->
      Arrays.stream(RoleName.values()).forEach(roleName -> {
        if (roleRepository.findByName(roleName) == null) {
          RoleModel roleModel = new RoleModel();
          roleModel.setName(roleName);

          roleRepository.save(roleModel);

          System.out.println("Role " + roleName + " created");
        } else {
          System.out.println("Role " + roleName + " already exists");
        }
      });
  }
}
