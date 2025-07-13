package dev.luisghtz.platzi_pizzeria_jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
            .requestMatchers(HttpMethod.GET, "/api/pizzas/**")
            .hasAnyRole("ADMIN", "CUSTOMER")
            .requestMatchers(HttpMethod.POST, "/api/pizzas/**").hasRole("ADMIN")
            .requestMatchers("/api/orders/random").hasAuthority("random_order")
            .requestMatchers("/api/orders/**").hasRole("ADMIN")
            .anyRequest().authenticated())
        .httpBasic(hb -> {
        });

    return http.build();
  }

  @Bean
  AuthenticationManager authenticationManager(AuthenticationConfiguration conf) throws Exception {
    return conf.getAuthenticationManager();
  }

  // @Bean
  // UserDetailsService userDetailsService() {
  // UserDetails admin = User.builder()
  // .username("admin")
  // .password(passwordEncoder().encode("admin"))
  // .roles("ADMIN")
  // .build();

  // UserDetails customer = User.builder()
  // .username("customer")
  // .password(passwordEncoder().encode("customer"))
  // .roles("CUSTOMER")
  // .build();

  // return new InMemoryUserDetailsManager(admin, customer);
  // }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
