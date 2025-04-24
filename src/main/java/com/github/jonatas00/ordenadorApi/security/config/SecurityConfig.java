package com.github.jonatas00.ordenadorApi.security.config;

import com.github.jonatas00.ordenadorApi.security.authentication.UserAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http, UserAuthenticationFilter userAuthenticationFilter) throws Exception {
    http
      .csrf(AbstractHttpConfigurer::disable)
      .httpBasic(Customizer.withDefaults())
      .authorizeHttpRequests(authorize -> authorize
        .requestMatchers(
          "/swagger-ui/**",
          "/swagger-ui.html",
          "/v3/api-docs/**",
          "/swagger-resources/**",
          "/webjars/**"
        ).permitAll()
        .requestMatchers(
          "/user/**",
          "/user/register",
          "/user/login"
        ).permitAll()
        .anyRequest().authenticated()
      )
      .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
