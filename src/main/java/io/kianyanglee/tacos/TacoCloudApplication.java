package io.kianyanglee.tacos;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.kianyanglee.tacos.repositories.IngredientRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class TacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

	@Bean
	public ApplicationRunner dataLoader(IngredientRepository repo) {
		log.info("Method dataLoader is called");
		return null;
		// return args -> {
		// 	repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
		// 	repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
		// 	repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
		// 	repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
		// 	repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
		// 	repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
		// 	repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
		// 	repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
		// 	repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
		// 	repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
		// };
	}
}
