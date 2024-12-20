package org.example.libman;

import org.example.libman.entities.Book;
import org.example.libman.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    // Spring Boot runs ALL CommandLineRunner beans once the application context is loaded
    @Bean
    CommandLineRunner initDatabase(BookRepository repository) {
        // Requests a copy of the repository
        return args -> {
            // Creates two Book entities to store into the repository
            log.info("Preloading " + repository.save(new Book("The Apothecary Diaries: Volume 1", "Natsu Hyuuga")));
            log.info("Preloading " + repository.save(new Book("Frieren: Beyond Journey's End", "Kanehito Yamada")));
        };
    }
}
