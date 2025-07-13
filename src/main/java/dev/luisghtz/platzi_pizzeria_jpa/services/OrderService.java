package dev.luisghtz.platzi_pizzeria_jpa.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.luisghtz.platzi_pizzeria_jpa.percistence.entity.OrderEntity;
import dev.luisghtz.platzi_pizzeria_jpa.percistence.projection.OrderSummary;
import dev.luisghtz.platzi_pizzeria_jpa.percistence.repository.OrderRepository;
import dev.luisghtz.platzi_pizzeria_jpa.services.dto.RandomOrderDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;
  private final String DELIVERY = "D";
  private final String CARRYOUT = "C";
  private final String IN_SITE = "S";

  public List<OrderEntity> getAll() {
    return orderRepository.findAll();
  }

  public List<OrderEntity> getAllAfterDate() {
    LocalDateTime today = LocalDate.now().atTime(0, 0);
    return orderRepository.findAllByDateAfter(today);
  }

  public List<OrderEntity> getOutsideOrders() {
    List<String> methods = List.of(DELIVERY, CARRYOUT);
    return orderRepository.findAllByMethodIn(methods);
  }

  public List<OrderEntity> getInSiteOrders() {
    List<String> methods = List.of(IN_SITE);
    return orderRepository.findAllByMethodIn(methods);
  }

  public List<OrderEntity> getCustomerOrders(String idCustomer) {
    return orderRepository.findCustomerOrders(idCustomer);
  }

  public OrderSummary getOrderSummary(int orderId) {
    return orderRepository.findSummary(orderId);
  }

  public boolean saveRandomOrder(RandomOrderDto randomOrderDto) {
    return orderRepository.saveRandomOrder(
        randomOrderDto.getIdCustomer(),
        randomOrderDto.getMethod());
  }
}
