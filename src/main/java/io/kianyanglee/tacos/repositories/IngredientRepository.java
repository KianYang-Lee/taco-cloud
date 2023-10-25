package io.kianyanglee.tacos.repositories;

import org.springframework.data.repository.CrudRepository;

import io.kianyanglee.tacos.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
