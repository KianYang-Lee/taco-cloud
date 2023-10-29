package io.kianyanglee.tacos.services;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import io.kianyanglee.tacos.domain.Ingredient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RestAPIConsumer {

    private RestTemplate restTemplate;

    public RestAPIConsumer(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Ingredient getIngredientById(String ingredientId) {
        return restTemplate.getForObject("http://localhost:8080/data-api/ingredients/{id}", Ingredient.class,
                ingredientId);
    }

    public Ingredient getIngredientByIdMapVersion(String ingredientId) {
        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("id", ingredientId);
        return restTemplate.getForObject("http://localhost:8080/data-api/ingredients/{id}", Ingredient.class,
                urlVariables);
    }

    public Ingredient getIngredientByIdURIVersion(String ingredientId) {
        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("id", ingredientId);
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/data-api/ingredients/{id}")
                .build(urlVariables);
        return restTemplate.getForObject(uri, Ingredient.class);
    }

    public Ingredient getIngredientByIdForEntity(String ingredientId) {
        ResponseEntity<Ingredient> responseEntity = restTemplate
                .getForEntity("http://localhost:8080/data-api/ingredients/{id}", Ingredient.class, ingredientId);
        log.info("Fetched time : {}", responseEntity.getHeaders().getDate());
        return responseEntity.getBody();
    }

    public void updateIngredient(Ingredient ingredient) {
        restTemplate.put("http://localhost:8080/data-api/ingredients/{id}", ingredient, ingredient.getId());
    }

    public void deleteIngredient(Ingredient ingredient) {
        restTemplate.delete("http://localhost:8080/data-api/ingredients/{id}", ingredient, ingredient.getId());
    }

    public void createIngredientWithObject(Ingredient ingredient) {
        restTemplate.postForObject("http://localhost:8080/data-api/ingredients", ingredient, Ingredient.class);
    }

    public URI createIngredientWithLocation(Ingredient ingredient) {
        return restTemplate.postForLocation("http://localhost:8080/data-api/ingredients", ingredient, Ingredient.class);
    }
    
    public Ingredient createIngredientWithEntity(Ingredient ingredient) {
        ResponseEntity<Ingredient> responseEntity = restTemplate.postForEntity("http://localhost:8080/data-api/ingredients", ingredient, Ingredient.class);
        log.info("New resource created at : {}", responseEntity.getHeaders().getLocation());
        return responseEntity.getBody();
    }
}
