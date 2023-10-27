package io.kianyanglee.tacos;

import java.util.Arrays;
import java.util.Date;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

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
			OrderRepository orderRepository) {
		log.info("Method dataLoader is called to load data for JPA");
		return args -> {
			Ingredient ingredient = new Ingredient("FLTO", "Flour Tortilla", Type.WRAP);
			ingredientRepository.save(ingredient);
			ingredientRepository.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
			ingredientRepository.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
			ingredientRepository.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
			ingredientRepository.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
			ingredientRepository.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
			ingredientRepository.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
			ingredientRepository.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
			ingredientRepository.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
			ingredientRepository.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
			Role userRole = new Role(RoleType.ROLE_USER.toString());
			Role adminRole = new Role(RoleType.ROLE_ADMIN.toString());
			roleRepository.save(userRole);
			roleRepository.save(adminRole);
			User user = new User("admin", "$2a$10$5nCRcUGuTFsHPPD4JVDNfO54p2eBfSVN8pO4si3gZJzbmiB5Ssv0S",
					"admin", "street", "city", "MY", "43200", "0123456789", Arrays.asList(userRole, adminRole));
			userRepository.save(user);
			orderRepository.save(new TacoOrder(null, new Date(), "Sanchez", "Street", "City",
					"NZ", "45000", "371449635398431", "12/22",
					"123", user, Arrays.asList(new Taco(null, new Date(), "ataco", Arrays.asList(ingredient)))));
		};
	}
}
