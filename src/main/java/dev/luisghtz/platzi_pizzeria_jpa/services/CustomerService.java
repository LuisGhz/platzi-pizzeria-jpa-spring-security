package dev.luisghtz.platzi_pizzeria_jpa.services;

import org.springframework.stereotype.Service;

import dev.luisghtz.platzi_pizzeria_jpa.percistence.entity.CustomerEntity;
import dev.luisghtz.platzi_pizzeria_jpa.percistence.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
  private final CustomerRepository customerRepository;

  public CustomerEntity getByPhone(String phone) {
    return customerRepository.findByPhone(phone);
  }
}
