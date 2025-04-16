package com.github.jonatas00.ordenadorApi.security.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.github.jonatas00.ordenadorApi.security.userdetails.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class JwtTokenService {

  @Value("${jwt.secret}")
  private String SECRET;

  @Value("${jwt.issuer}")
  private String ISSUER;

  public String generateToken(UserDetailsImpl user) {
    Algorithm algorithm = Algorithm.HMAC256(SECRET);
    return JWT.create()
      .withIssuer(ISSUER)
      .sign(algorithm);
  }

  private Instant creationDate() {
    return ZonedDateTime.now(ZoneId.of("America/SaoPaulo")).toInstant();
  }

  private Instant expirationDate() {
    return ZonedDateTime.now(ZoneId.of("America/SaoPaulo")).plusHours(4).toInstant();
  }
}
