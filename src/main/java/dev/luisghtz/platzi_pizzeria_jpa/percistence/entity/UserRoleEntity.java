package dev.luisghtz.platzi_pizzeria_jpa.percistence.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_role")
@IdClass(UserRoleId.class)
@Getter
@Setter
@NoArgsConstructor
public class UserRoleEntity {
  @Id
  @Column(nullable = false, length = 20)
  private String username;

  @Id
  @Column(nullable = false, length = 20)
  private String role;

  @Column(name = "granted_date", nullable = false, columnDefinition = "DATETIME")
  private LocalDateTime grantedDate;

  @ManyToOne
  @JoinColumn(name = "username", referencedColumnName = "username", insertable = false)
  private UserEntity user;
}
