package dev.luisghtz.platzi_pizzeria_jpa.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.luisghtz.platzi_pizzeria_jpa.percistence.entity.CustomerEntity;
import dev.luisghtz.platzi_pizzeria_jpa.services.CustomerService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
  private final CustomerService customerService;

  @GetMapping("phone")
  public ResponseEntity<CustomerEntity> getByPhone(@RequestParam String phone) {
    return ResponseEntity.ok(customerService.getByPhone(phone));
  }

}
