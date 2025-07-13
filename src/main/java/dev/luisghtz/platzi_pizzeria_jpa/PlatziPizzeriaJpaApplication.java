package dev.luisghtz.platzi_pizzeria_jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PlatziPizzeriaJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlatziPizzeriaJpaApplication.class, args);
	}

}
