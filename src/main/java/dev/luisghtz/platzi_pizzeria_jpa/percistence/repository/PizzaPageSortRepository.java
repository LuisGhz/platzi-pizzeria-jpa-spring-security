package dev.luisghtz.platzi_pizzeria_jpa.percistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import dev.luisghtz.platzi_pizzeria_jpa.percistence.entity.PizzaEntity;

public interface PizzaPageSortRepository extends ListPagingAndSortingRepository<PizzaEntity, Integer> {
  Page<PizzaEntity> findAllByAvailableTrue(Pageable pageable);
}
