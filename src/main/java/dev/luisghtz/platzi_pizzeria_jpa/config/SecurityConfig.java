package dev.luisghtz.platzi_pizzeria_jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .cors(cors -> {
        }) // Enable CORS with default settings
        .authorizeHttpRequests(authz -> authz
            .requestMatchers(HttpMethod.GET, "/api/**")
            .permitAll());

    return http.build();
  }
}
