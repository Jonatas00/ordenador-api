package com.github.jonatas00.ordenadorApi;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrdenadorApiApplication {

  public static void main(String[] args) {
    Dotenv dotenv = Dotenv.load();

    System.setProperty("DATABASE_USER", dotenv.get("DATABASE_USER"));
    System.setProperty("DATABASE_PASSWORD", dotenv.get("DATABASE_PASSWORD"));
    System.setProperty("DATABASE_NAME", dotenv.get("DATABASE_NAME"));

    SpringApplication.run(OrdenadorApiApplication.class, args);
  }

}

