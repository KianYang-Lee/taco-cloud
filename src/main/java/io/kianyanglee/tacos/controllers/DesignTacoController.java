package io.kianyanglee.tacos.controllers;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import io.kianyanglee.tacos.domain.Ingredient;
import io.kianyanglee.tacos.domain.Taco;
import io.kianyanglee.tacos.domain.TacoOrder;
import io.kianyanglee.tacos.domain.TacoUDT;
import io.kianyanglee.tacos.domain.Ingredient.Type;
import io.kianyanglee.tacos.repositories.IngredientRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    public DesignTacoController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        log.info("Calling addIngredientsToModel");
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();
        log.info("Ingredients are : {}", ingredients);

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
            log.info("Added attribute : {}", model.getAttribute(type.toString().toLowerCase()));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        log.info("Calling order method");
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        log.info("Calling taco method");
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        log.info("Calling showDesignForm method");
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                log.error("Error is : {}", error);

            }
            return "design";
        }

        tacoOrder.addTaco(new TacoUDT(taco));
        log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type) {
        return StreamSupport.stream(ingredients.spliterator(), false).filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
