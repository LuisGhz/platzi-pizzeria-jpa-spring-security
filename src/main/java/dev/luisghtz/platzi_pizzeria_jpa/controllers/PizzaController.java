package dev.luisghtz.platzi_pizzeria_jpa.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.luisghtz.platzi_pizzeria_jpa.percistence.entity.PizzaEntity;
import dev.luisghtz.platzi_pizzeria_jpa.services.PizzaService;
import dev.luisghtz.platzi_pizzeria_jpa.services.dto.UpdatePizzaPriceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/pizzas")
@RequiredArgsConstructor
public class PizzaController {
  private final PizzaService pizzaService;

  @GetMapping
  public ResponseEntity<Page<PizzaEntity>> getAll(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int elements) {
    return ResponseEntity.ok(pizzaService.getAll(page, elements));
  }

  @GetMapping("available")
  public ResponseEntity<Page<PizzaEntity>> getAvailable(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int elements,
      @RequestParam(defaultValue = "price") String sortBy,
      @RequestParam(defaultValue = "asc") String sortOrder) {
    return ResponseEntity.ok(pizzaService.getAvailable(page, elements, sortBy, sortOrder));
  }

  @GetMapping("byname")
  public ResponseEntity<List<PizzaEntity>> getMethodName(@RequestParam String name) {
    return ResponseEntity.ok(pizzaService.getByName(name));
  }

  @GetMapping("firstbyname")
  public ResponseEntity<PizzaEntity> getFirstByName(@RequestParam String name) {
    return ResponseEntity.ok(pizzaService.getFirstByName(name));
  }

  @GetMapping("cheaper")
  public ResponseEntity<List<PizzaEntity>> getCheaper(@RequestParam Double price) {
    return ResponseEntity.ok(pizzaService.getCheaper(price));
  }

  @GetMapping("with")
  public ResponseEntity<List<PizzaEntity>> getWith(@RequestParam String ingredient) {
    return ResponseEntity.ok(pizzaService.getWith(ingredient));
  }

  @GetMapping("without")
  public ResponseEntity<List<PizzaEntity>> getWithout(@RequestParam String ingredient) {
    return ResponseEntity.ok(pizzaService.getWithout(ingredient));
  }

  @GetMapping("{id}")
  public ResponseEntity<PizzaEntity> getById(@PathVariable int id) {
    return ResponseEntity.ok(pizzaService.getById(id));
  }

  @PostMapping
  public ResponseEntity<PizzaEntity> save(@RequestBody PizzaEntity entity) {
    if (entity.getId() == null || !pizzaService.exist(entity.getId())) {
      return new ResponseEntity<>(pizzaService.save(entity), HttpStatus.CREATED);
    }

    return ResponseEntity.badRequest().build();
  }

  @PutMapping
  public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity entity) {
    if (entity.getId() != null || pizzaService.exist(entity.getId())) {
      return new ResponseEntity<>(pizzaService.save(entity), HttpStatus.CREATED);
    }

    return ResponseEntity.badRequest().build();
  }

  @PutMapping("price/update")
  public ResponseEntity<String> putMethodName(@RequestBody UpdatePizzaPriceDto newPizzaPrice) {
    if (newPizzaPrice.getId() <= 0 || newPizzaPrice.getPrice() <= 0) {
      return ResponseEntity.badRequest().body("Invalid pizza ID or price");
    }
    if (!pizzaService.exist(newPizzaPrice.getId())) {
      return ResponseEntity.notFound().build();
    }
    pizzaService.updatePrice(newPizzaPrice);
    return ResponseEntity.ok("Pizza price updated successfully");
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable int id) {
    if (pizzaService.exist(id))
      pizzaService.delete(id);

    return ResponseEntity.noContent().build();
  }

}