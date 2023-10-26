package io.kianyanglee.tacos;

import java.util.Arrays;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.kianyanglee.tacos.domain.Ingredient;
import io.kianyanglee.tacos.domain.Role;
import io.kianyanglee.tacos.domain.User;
import io.kianyanglee.tacos.domain.Ingredient.Type;
import io.kianyanglee.tacos.domain.Role.RoleType;
import io.kianyanglee.tacos.repositories.IngredientRepository;
import io.kianyanglee.tacos.repositories.RoleRepository;
import io.kianyanglee.tacos.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class TacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

	@Bean
	public ApplicationRunner dataLoader(
			IngredientRepository ingredientRepository,
			RoleRepository roleRepository,
			UserRepository userRepository) {
		log.info("Method dataLoader is called to load data for JPA");
		return args -> {
			ingredientRepository.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
			ingredientRepository.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
			ingredientRepository.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
			ingredientRepository.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
			ingredientRepository.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
			ingredientRepository.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
			ingredientRepository.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
			ingredientRepository.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
			ingredientRepository.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
			ingredientRepository.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
			roleRepository.save(new Role(RoleType.ROLE_USER.toString()));
			roleRepository.save(new Role(RoleType.ROLE_ADMIN.toString()));
			Role userRole = roleRepository.findByRole(RoleType.ROLE_USER.toString());
			Role adminRole = roleRepository.findByRole(RoleType.ROLE_ADMIN.toString());
			userRepository.save(new User("admin", "$2a$10$5nCRcUGuTFsHPPD4JVDNfO54p2eBfSVN8pO4si3gZJzbmiB5Ssv0S",
					"admin", "street", "city", "MY", "43200", "0123456789", Arrays.asList(userRole, adminRole)));
		};
	}
}
