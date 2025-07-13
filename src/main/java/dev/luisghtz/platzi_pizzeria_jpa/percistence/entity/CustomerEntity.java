package dev.luisghtz.platzi_pizzeria_jpa.percistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Setter
@Getter
@NoArgsConstructor
public class CustomerEntity {
  @Id
  @Column(length = 15, nullable = false)
  private String id;

  @Column(nullable = false, length = 60)
  private String name;

  @Column(nullable = false, length = 1000)
  private String address;

  @Column(nullable = false, length = 50)
  private String email;

  @Column(nullable = false, name = "phone_number")
  private String phoneNumber;
}
