package dev.luisghtz.platzi_pizzeria_jpa.percistence.repository;

import org.springframework.data.repository.CrudRepository;

import dev.luisghtz.platzi_pizzeria_jpa.percistence.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, String> {

}
