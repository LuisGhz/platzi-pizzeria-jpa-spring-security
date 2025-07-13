package dev.luisghtz.platzi_pizzeria_jpa.config;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

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
}
