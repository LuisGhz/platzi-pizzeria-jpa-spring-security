package dev.luisghtz.platzi_pizzeria_jpa.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.luisghtz.platzi_pizzeria_jpa.percistence.entity.OrderEntity;
import dev.luisghtz.platzi_pizzeria_jpa.services.OrderService;
import dev.luisghtz.platzi_pizzeria_jpa.services.dto.RandomOrderDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
  private final OrderService orderService;

  @GetMapping()
  public ResponseEntity<List<OrderEntity>> getMethodName() {
    return ResponseEntity.ok(orderService.getAll());
  }

  @GetMapping("/customer")
  public ResponseEntity<List<OrderEntity>> getCustomerOrders(@RequestParam String id) {
    return ResponseEntity.ok(orderService.getCustomerOrders(id));
  }

  @GetMapping("/today")
  public ResponseEntity<List<OrderEntity>> getAllAfterDate() {
    return ResponseEntity.ok(orderService.getAllAfterDate());
  }

  @GetMapping("/outside")
  public ResponseEntity<List<OrderEntity>> getOutsideOrders() {
    return ResponseEntity.ok(orderService.getOutsideOrders());
  }

  @GetMapping("/inside")
  public ResponseEntity<List<OrderEntity>> getInSiteOrders() {
    return ResponseEntity.ok(orderService.getInSiteOrders());
  }

  @GetMapping("/summary")
  public ResponseEntity<?> getOrderSummary(@RequestParam int orderId) {
    return ResponseEntity.ok(orderService.getOrderSummary(orderId));
  }

  @PostMapping("/random")
  public ResponseEntity<?> saveRandomOrder(@RequestBody RandomOrderDto randomOrderDto) {
    boolean saved = orderService.saveRandomOrder(randomOrderDto);
    if (saved) {
      return ResponseEntity.ok("Order saved successfully");
    } else {
      return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body("Failed to save order");
    }
  }

}
