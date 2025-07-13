package dev.luisghtz.platzi_pizzeria_jpa.percistence.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleId implements Serializable {
  private String username;
  private String role;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserRoleId that = (UserRoleId) o;
    return java.util.Objects.equals(username, that.username) &&
         java.util.Objects.equals(role, that.role);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(username, role);
  }
}
