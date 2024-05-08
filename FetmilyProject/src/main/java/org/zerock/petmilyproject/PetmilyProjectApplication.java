package org.zerock.petmilyproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PetmilyProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetmilyProjectApplication.class, args);
    }

}
