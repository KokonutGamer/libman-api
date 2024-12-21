package org.example.libman;

import org.example.libman.entities.Book;
import org.example.libman.entities.BookStatus;
import org.example.libman.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    // Spring Boot runs ALL CommandLineRunner beans once the application context is loaded
    @Bean
    CommandLineRunner initDatabase(BookRepository repository) {
        // Requests a copy of the repository
        return args -> {
            // Creates two Book entities to store into the repository
            log.info("Preloading " + repository.save(new Book(
                    "The Apothecary Diaries: Volume 1",
                    "Natsu Hyuuga",
                    1,
                    1,
                    178,
                    LocalDate.of(2020, 12, 8),
                    BookStatus.AVAILABLE)));
            log.info("Preloading " + repository.save(new Book(
                    "Frieren: Beyond Journey's End",
                    "Kanehito Yamada",
                    1,
                    1,
                    192,
                    LocalDate.of(2021, 11, 9),
                    BookStatus.AVAILABLE)));
        };
    }
}
