package dev.luisghtz.platzi_pizzeria_jpa.percistence.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
  @Id
  @Column(nullable = false, length = 20)
  private String username;

  @Column(nullable = false, length = 200)
  private String password;

  @Column(length = 50)
  private String email;

  @Column(nullable = false, columnDefinition = "TINYINT")
  private Boolean locked;

  @Column(nullable = false, columnDefinition = "TINYINT")
  private Boolean disabled;

  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
  private List<UserRoleEntity> roles;
}
