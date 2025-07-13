package dev.luisghtz.platzi_pizzeria_jpa.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
// import org.springframework.jdbc.core.BeanPropertyRowMapper;
// import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import dev.luisghtz.platzi_pizzeria_jpa.percistence.entity.PizzaEntity;
import dev.luisghtz.platzi_pizzeria_jpa.percistence.repository.PizzaPageSortRepository;
import dev.luisghtz.platzi_pizzeria_jpa.percistence.repository.PizzaRepository;
import dev.luisghtz.platzi_pizzeria_jpa.services.dto.UpdatePizzaPriceDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PizzaService {
  // private final JdbcTemplate jdbcTemplate;
  private final PizzaRepository pizzaRepository;
  private final PizzaPageSortRepository pizzaPageSortRepository;

  public Page<PizzaEntity> getAll(int page, int elements) {
    Pageable pageable = PageRequest.of(page, elements);
    return pizzaPageSortRepository.findAll(pageable);
    // return this.jdbcTemplate.query("SELECT * FROM pizza", new
    // BeanPropertyRowMapper<>(PizzaEntity.class));
  }

  public Page<PizzaEntity> getAvailable(int page, int elements, String sortBy, String sortOrder) {
    Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
    Pageable pageable = PageRequest.of(page, elements, sort);
    return pizzaPageSortRepository.findAllByAvailableTrue(pageable);
  }

  public List<PizzaEntity> getByName(String name) {
    return pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
  }

  public PizzaEntity getFirstByName(String name) {
    return pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Pizza not found with name: " + name));
  }

  public List<PizzaEntity> getWith(String ingredient) {
    return pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
  }

  public List<PizzaEntity> getWithout(String ingredient) {
    return pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
  }

  public List<PizzaEntity> getCheaper(Double price) {
    return pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
  }

  public PizzaEntity getById(int id) {
    return pizzaRepository.findById(id).orElse(null);
  }

  public PizzaEntity save(PizzaEntity pizza) {
    return pizzaRepository.save(pizza);
  }

  @Transactional
  public void updatePrice(UpdatePizzaPriceDto updatePizzaPriceDto) {
    pizzaRepository.updatePrice(updatePizzaPriceDto);
  }

  public void delete(int id) {
    pizzaRepository.deleteById(id);
  }

  public Boolean exist(int id) {
    return pizzaRepository.existsById(id);
  }
}
