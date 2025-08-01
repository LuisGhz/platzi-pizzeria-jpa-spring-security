package dev.luisghtz.platzi_pizzeria_jpa.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.luisghtz.platzi_pizzeria_jpa.config.JwtUtil;
import dev.luisghtz.platzi_pizzeria_jpa.services.dto.LoginDto;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtUtil;

  @PostMapping("login")
  public ResponseEntity<Void> login(@RequestBody LoginDto loginDto) {
    UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
        loginDto.getPassword());

    Authentication authentication = authenticationManager.authenticate(login);
    System.out.println(authentication.isAuthenticated());
    System.out.println(authentication.getPrincipal());

    String jwt = jwtUtil.create(loginDto.getUsername());
    return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
  }

}
