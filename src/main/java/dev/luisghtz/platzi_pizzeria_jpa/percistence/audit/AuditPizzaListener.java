package dev.luisghtz.platzi_pizzeria_jpa.percistence.audit;

import dev.luisghtz.platzi_pizzeria_jpa.percistence.entity.PizzaEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;

public class AuditPizzaListener {

  private PizzaEntity currentValue;

  @PostLoad
  public void postLoad(PizzaEntity entity) {
    System.out.println("Pizza loaded");
    // Use new constructor to create a copy of the entity
    // I'm not using SerializationUtils.clone because of spring boot devtools
    currentValue = new PizzaEntity(entity);
  }

  @PostPersist
  @PostUpdate
  public void audit(PizzaEntity entity) {
    System.out.println("POST UPDATE: " + entity.toString());
    if (currentValue != null) {
      System.out.println("Previous value: " + currentValue.toString());
    } else {
      System.out.println("No previous value available.");
    }
  }

  @PreRemove
  public void preRemove(PizzaEntity entity) {
    System.out.println("Pizza will be removed: " + entity.toString());
  }
}
