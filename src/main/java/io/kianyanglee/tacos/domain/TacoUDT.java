package io.kianyanglee.tacos.domain;

import java.util.List;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import lombok.Data;

@Data
@UserDefinedType("taco")
public class TacoUDT {
    public TacoUDT(Taco taco) {
        this.name = taco.getName();
        this.ingredients = taco .getIngredients();
    }
    private final String name;
    private final List<IngredientUDT> ingredients;
}
