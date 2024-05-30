package pro.dev.animalshelter;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@OpenAPIDefinition // http://localhost:8080/swagger-ui/index.html#/
public class AnimalShelterApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnimalShelterApplication.class, args);
    }
}
