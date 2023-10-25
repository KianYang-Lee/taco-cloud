package io.kianyanglee.tacos.domain;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@UserDefinedType("ingredient")
public class IngredientUDT {

    public IngredientUDT(Ingredient ingredient) {
        this.name = ingredient.getName();
        this.type = ingredient.getType();
    }

    private final String name;    

    private final Ingredient.Type type;
}
