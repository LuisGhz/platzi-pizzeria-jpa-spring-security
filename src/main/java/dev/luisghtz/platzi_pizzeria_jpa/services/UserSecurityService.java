package dev.luisghtz.platzi_pizzeria_jpa.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.luisghtz.platzi_pizzeria_jpa.percistence.entity.UserRoleEntity;
import dev.luisghtz.platzi_pizzeria_jpa.percistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var userEntity = userRepository.findById(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    String[] roles = userEntity.getRoles()
        .stream().map(UserRoleEntity::getRole).toArray(String[]::new);
    return User.builder()
        .username(username)
        .password(userEntity.getPassword())
        // .roles(roles)
        .authorities(grantedAuthorities(roles))
        .accountLocked(userEntity.getLocked())
        .disabled(userEntity.getDisabled())
        .build();
  }

  private String[] getAuthorities(String role) {
    if ("ADMIN".equals(role) || "CUSTOMER".equals(role)) {
      return new String[] { "random_order" };
    }

    return new String[] {};
  }

  private List<GrantedAuthority> grantedAuthorities(String[] roles) {
    List<GrantedAuthority> authorities = new ArrayList<>(roles.length);

    for (String role : roles) {
      authorities.add(new SimpleGrantedAuthority("ROLE_" + role));

      for(String authority: this.getAuthorities(role)) {
        authorities.add(new SimpleGrantedAuthority(authority));
      }
    }

    return authorities;
  }
}
