package com.github.jonatas00.ordenadorApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class OrdenadorApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(OrdenadorApiApplication.class, args);
  }
}

