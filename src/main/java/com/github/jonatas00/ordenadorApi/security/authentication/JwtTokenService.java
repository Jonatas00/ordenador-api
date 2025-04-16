package com.github.jonatas00.ordenadorApi.security.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
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
  private static String ISSUER;

  private static final long EXPIRATION_TIME_SECONDS = 60 * 60 * 4;

  public String generateToken(UserDetailsImpl user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(SECRET);
      return JWT.create()
        .withIssuer(ISSUER)
        .withIssuedAt(Instant.now())
        .withExpiresAt(Instant.now().plusSeconds(EXPIRATION_TIME_SECONDS))
        .withSubject(user.getUsername())
        .sign(algorithm);

    } catch (JWTCreationException exception) {
      throw new JWTCreationException("Erro ao gerar token", exception);
    }
  }

  public String getUsernameFromToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(SECRET);

      return JWT.require(algorithm)
        .withIssuer(ISSUER)
        .build()
        .verify(token)
        .getSubject();

    } catch (Exception exception) {
      throw new JWTCreationException("Token inválido ou expirado", exception);
    }
  }
}
