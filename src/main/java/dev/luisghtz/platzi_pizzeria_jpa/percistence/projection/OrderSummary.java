package dev.luisghtz.platzi_pizzeria_jpa.percistence.projection;

import java.time.LocalDateTime;

public interface OrderSummary {
    Integer getIdOrder();
    String getCustomerName();
    LocalDateTime getOrderDate();
    String getPizzaNames();
    Double getOrderTotal();
}