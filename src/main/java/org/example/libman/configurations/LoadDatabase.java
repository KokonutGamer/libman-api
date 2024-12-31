package org.example.libman.configurations;

import org.example.libman.entities.Book;
import org.example.libman.entities.User;
import org.example.libman.repositories.BookRepository;
import org.example.libman.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Date;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    // Spring Boot runs ALL CommandLineRunner beans once the application context is loaded
    @Bean
    CommandLineRunner initDatabase(BookRepository bookRepository, UserRepository userRepository) {
        // Requests a copy of the repository
        String logText = "Preloading {}";
        return args -> {
            // Creates two Book entities to store into the repository
            log.info(logText, bookRepository.save(new Book(
                    "The Apothecary Diaries: Volume 1",
                    "Natsu Hyuuga",
                    1,
                    1,
                    178,
                    LocalDate.of(2020, 12, 8),
                    3,
                    5)));
            log.info(logText, bookRepository.save(new Book(
                    "Frieren: Beyond Journey's End",
                    "Kanehito Yamada",
                    1,
                    1,
                    192,
                    LocalDate.of(2021, 11, 9),
                    0,
                    7)));
            log.info(logText, userRepository.save(new User(
                    "User",
                    "user@gmail.com",
                    "UserPW123#$",
                    new Date(System.currentTimeMillis()),
                    new Date(System.currentTimeMillis())
            )));
        };
    }
}
