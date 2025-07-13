package dev.luisghtz.platzi_pizzeria_jpa.percistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import dev.luisghtz.platzi_pizzeria_jpa.percistence.entity.PizzaEntity;
import dev.luisghtz.platzi_pizzeria_jpa.services.dto.UpdatePizzaPriceDto;

public interface PizzaRepository extends CrudRepository<PizzaEntity, Integer> {
  List<PizzaEntity> findAllByAvailableTrueOrderByPrice();

  List<PizzaEntity> findAllByAvailableTrueAndNameIgnoreCase(String name);

  Optional<PizzaEntity> findFirstByAvailableTrueAndNameIgnoreCase(String name);

  List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);

  List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);

  List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(Double price);

  long countByVeganTrue();

  @Modifying
  @Query(value = "UPDATE pizza SET price = :#{#newPizzaPrice.price} WHERE id = :#{#newPizzaPrice.id}", nativeQuery = true)
  void updatePrice(@Param("newPizzaPrice") UpdatePizzaPriceDto newPizzaPriceDto);
}
