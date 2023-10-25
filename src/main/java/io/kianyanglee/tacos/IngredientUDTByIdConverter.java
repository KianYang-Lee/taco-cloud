package io.kianyanglee.tacos;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import io.kianyanglee.tacos.domain.Ingredient;
import io.kianyanglee.tacos.domain.IngredientUDT;
import io.kianyanglee.tacos.repositories.IngredientRepository;

@Component
public class IngredientUDTByIdConverter implements Converter<String, IngredientUDT> {

    private IngredientRepository ingredientRepository;

    public IngredientUDTByIdConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public IngredientUDT convert(String id) {
        Ingredient ingredient = ingredientRepository.findById(id).orElse(null);
        return new IngredientUDT(ingredient);
    }
}
