package hr.fer.progi.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class BackendApplication {

    static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Profile("dev")
    @Bean
    CommandLineRunner privremenoPopuniBazu() {
        return args -> {
            System.out.println("runner");
        };
    }
}
