package io.kianyanglee.tacos;

import java.util.Arrays;
import java.util.Date;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.kianyanglee.tacos.domain.Ingredient;
import io.kianyanglee.tacos.domain.Ingredient.Type;
import io.kianyanglee.tacos.domain.Role;
import io.kianyanglee.tacos.domain.Role.RoleType;
import io.kianyanglee.tacos.domain.Taco;
import io.kianyanglee.tacos.domain.TacoOrder;
import io.kianyanglee.tacos.domain.User;
import io.kianyanglee.tacos.repositories.IngredientRepository;
import io.kianyanglee.tacos.repositories.OrderRepository;
import io.kianyanglee.tacos.repositories.RoleRepository;
import io.kianyanglee.tacos.repositories.TacoRepository;
import io.kianyanglee.tacos.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@Profile("!prod")
public class DevelopmentConfig {

    @Bean
    public ApplicationRunner dataLoader(
            IngredientRepository ingredientRepository,
            RoleRepository roleRepository,
            UserRepository userRepository,
            OrderRepository orderRepository,
            TacoRepository tacoRepository,
            PasswordEncoder encoder) {
        log.info("Method dataLoader is called to load data for JPA");
        return args -> {
            Ingredient flourTortilla = new Ingredient("FLTO", "Flour Tortilla", Type.WRAP);
            Ingredient cornTortilla = new Ingredient("COTO", "Corn Tortilla", Type.WRAP);
            Ingredient groundBeef = new Ingredient("GRBF", "Ground Beef", Type.PROTEIN);
            Ingredient carnitas = new Ingredient("CARN", "Carnitas", Type.PROTEIN);
            Ingredient dicedTomatoes = new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES);
            Ingredient lettuce = new Ingredient("LETC", "Lettuce", Type.VEGGIES);
            Ingredient cheddar = new Ingredient("CHED", "Cheddar", Type.CHEESE);
            Ingredient jack = new Ingredient("JACK", "Monterrey Jack", Type.CHEESE);
            Ingredient salsa = new Ingredient("SLSA", "Salsa", Type.SAUCE);
            Ingredient sourCream = new Ingredient("SRCR", "Sour Cream", Type.SAUCE);
            ingredientRepository.save(flourTortilla);
            ingredientRepository.save(cornTortilla);
            ingredientRepository.save(groundBeef);
            ingredientRepository.save(carnitas);
            ingredientRepository.save(dicedTomatoes);
            ingredientRepository.save(lettuce);
            ingredientRepository.save(cheddar);
            ingredientRepository.save(jack);
            ingredientRepository.save(salsa);
            ingredientRepository.save(sourCream);

            Taco taco1 = new Taco();
            taco1.setName("Carnivore");
            taco1.setIngredients(Arrays.asList(
                    flourTortilla, groundBeef, carnitas,
                    sourCream, salsa, cheddar));
            tacoRepository.save(taco1);
            Taco taco2 = new Taco();
            taco2.setName("Bovine Bounty");
            taco2.setIngredients(Arrays.asList(
                    cornTortilla, groundBeef, cheddar,
                    jack, sourCream));
            tacoRepository.save(taco2);
            Taco taco3 = new Taco();
            taco3.setName("Veg-Out");
            taco3.setIngredients(Arrays.asList(
                    flourTortilla, cornTortilla, dicedTomatoes,
                    lettuce, salsa));
            tacoRepository.save(taco3);
            
            Role userRole = new Role(RoleType.ROLE_USER.toString());
            Role adminRole = new Role(RoleType.ROLE_ADMIN.toString());
            roleRepository.save(userRole);
            roleRepository.save(adminRole);
            User user = new User("admin", encoder.encode("password"),
                    "admin", "street", "city", "MY", "43200", "0123456789", Arrays.asList(userRole, adminRole));
            
                
            userRepository.save(user);
            orderRepository.save(new TacoOrder(null, new Date(), "Sanchez", "Street", "City",
                    "NZ", "45000", "371449635398431", "12/22",
                    "123", user, Arrays.asList(new Taco(null, new Date(), "ataco", Arrays.asList(flourTortilla)))));
        };
    }
}
