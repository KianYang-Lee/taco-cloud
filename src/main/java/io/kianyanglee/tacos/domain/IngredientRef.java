package io.kianyanglee.tacos.domain;

import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Table
@Data
public class IngredientRef {
    private final String ingredient;    
}
