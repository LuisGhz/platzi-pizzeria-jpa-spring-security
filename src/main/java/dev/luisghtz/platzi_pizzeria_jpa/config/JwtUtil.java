package dev.luisghtz.platzi_pizzeria_jpa.config;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Component
public class JwtUtil {
  private static String secretKey = "s3cr3tk3y123";
  private static Algorithm ALGORITHM = Algorithm.HMAC256(secretKey);

  public String create(String username) {
    return JWT.create()
        .withSubject(username)
        .withIssuer("platzi-pizzeria")
        .withIssuedAt(new Date())
        .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)))
        .sign(ALGORITHM);
  }

  public boolean isValid(String jwt) {
    try {
      JWT.require(ALGORITHM).build().verify(jwt);
      return true;
    } catch (JWTVerificationException ex) {
      return false;
    }
  }

  public String getUsername(String jwt) {
    return JWT.require(ALGORITHM)
        .build()
        .verify(jwt)
        .getSubject();
  }
}
