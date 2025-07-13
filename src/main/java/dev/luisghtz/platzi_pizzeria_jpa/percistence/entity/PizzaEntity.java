package dev.luisghtz.platzi_pizzeria_jpa.percistence.entity;

import java.io.Serializable;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import dev.luisghtz.platzi_pizzeria_jpa.percistence.audit.AuditPizzaListener;
import dev.luisghtz.platzi_pizzeria_jpa.percistence.audit.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "pizza")
@EntityListeners({ AuditingEntityListener.class, AuditPizzaListener.class })
@Setter
@Getter
@NoArgsConstructor
@ToString
public class PizzaEntity extends AuditableEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Integer id;

  @Column(nullable = false, length = 30, unique = true)
  private String name;

  @Column(nullable = false, length = 150)
  private String description;

  @Column(nullable = false, columnDefinition = "Decimal(5,2)")
  private Double price;

  @Column(columnDefinition = "TINYINT")
  private Boolean vegetarian;

  @Column(columnDefinition = "TINYINT")
  private Boolean vegan;

  @Column(columnDefinition = "TINYINT", nullable = false)
  private Boolean available;

  // Manual copy constructor
  public PizzaEntity(PizzaEntity other) {
    this.id = other.id;
    this.name = other.name;
    this.description = other.description;
    this.price = other.price;
    this.vegetarian = other.vegetarian;
    this.vegan = other.vegan;
    this.available = other.available;
  }
}
