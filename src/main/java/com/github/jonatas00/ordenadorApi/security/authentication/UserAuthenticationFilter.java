package com.github.jonatas00.ordenadorApi.security.authentication;

import com.github.jonatas00.ordenadorApi.entities.UserModel;
import com.github.jonatas00.ordenadorApi.repository.UserRepository;
import com.github.jonatas00.ordenadorApi.security.userdetails.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {

  private final JwtTokenService jwtTokenService;
  private final UserRepository userRepository;

  public UserAuthenticationFilter(
    JwtTokenService jwtTokenService, UserRepository userRepository) {
    this.jwtTokenService = jwtTokenService;
    this.userRepository = userRepository;
  }

  @Override
  protected void doFilterInternal(
    @NonNull HttpServletRequest request,
    @NonNull HttpServletResponse response,
    @NonNull FilterChain filterChain
  ) throws ServletException, IOException {
    String authHeader = request.getHeader("Authorization");

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      String token = authHeader.substring(7);

      try {
        String username = jwtTokenService.getUsernameFromToken(token);

        Optional<UserModel> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
          UserDetailsImpl userDetails = new UserDetailsImpl(userOptional.get());

          Authentication authentication = new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities()
          );

          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      } catch (Exception e) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return;
      }
    }
    filterChain.doFilter(request, response);
  }
}
