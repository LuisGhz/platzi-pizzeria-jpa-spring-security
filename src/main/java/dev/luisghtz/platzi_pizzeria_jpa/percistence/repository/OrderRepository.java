package dev.luisghtz.platzi_pizzeria_jpa.percistence.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import dev.luisghtz.platzi_pizzeria_jpa.percistence.entity.OrderEntity;
import dev.luisghtz.platzi_pizzeria_jpa.percistence.projection.OrderSummary;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {
  List<OrderEntity> findAllByDateAfter(LocalDateTime date);

  List<OrderEntity> findAllByMethodIn(List<String> methods);

  @Query(value = "SELECT * FROM pizza_order WHERE id_customer = :idCustomer", nativeQuery = true)
  List<OrderEntity> findCustomerOrders(@Param("idCustomer") String idCustomer);

  @Query(value = """
      SELECT
        po.id AS idOrder,
        cu.name AS customerName,
        po.date AS orderDate,
        po.total AS orderTotal,
        GROUP_CONCAT(pi.name) AS pizzaNames
      FROM pizza_order po
      INNER JOIN customer cu ON po.id_customer = cu.id
      INNER JOIN order_item oi ON po.id = oi.id_order
      INNER JOIN pizza pi ON oi.id_pizza = pi.id
      WHERE po.id = :orderId
      GROUP BY po.id, cu.name, po.date, po.total
      """, nativeQuery = true)
  OrderSummary findSummary(@Param("orderId") int orderId);

  @Procedure(value = "taken_random_pizza_order", outputParameterName = "order_taken")
  boolean saveRandomOrder(@Param("id_customer") String idCustomer, @Param("method") String method);
}
