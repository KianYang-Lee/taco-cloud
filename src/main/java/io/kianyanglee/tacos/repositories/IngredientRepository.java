package io.kianyanglee.tacos.repositories;

import java.util.List;
import java.util.Optional;

import io.kianyanglee.tacos.domain.Ingredient;

public interface IngredientRepository {
    
    List<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);
}
