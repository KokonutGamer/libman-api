package org.example.libman;

import org.example.libman.entities.Book;
import org.example.libman.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.time.LocalDate;

@Configuration
@EnableMethodSecurity
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    // Spring Boot runs ALL CommandLineRunner beans once the application context is loaded
    @Bean
    CommandLineRunner initDatabase(BookRepository repository) {
        // Requests a copy of the repository
        String logText = "Preloading {}";
        return args -> {
            // Creates two Book entities to store into the repository
            log.info(logText, repository.save(new Book(
                    "The Apothecary Diaries: Volume 1",
                    "Natsu Hyuuga",
                    1,
                    1,
                    178,
                    LocalDate.of(2020, 12, 8),
                    3,
                    5)));
            log.info(logText, repository.save(new Book(
                    "Frieren: Beyond Journey's End",
                    "Kanehito Yamada",
                    1,
                    1,
                    192,
                    LocalDate.of(2021, 11, 9),
                    0,
                    7)));
        };
    }

    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails user = User.withUsername("user").password(encoder.encode("UserPW123#$")).roles("USER").build();
        UserDetails admin = User.withUsername("admin").password(encoder.encode("AdminPW123#$")).roles("ADMIN").build();
        UserDetails librarian = User.withUsername("librarian").password(encoder.encode("LibPW123#$")).roles("LIBRARIAN").build();
        return new InMemoryUserDetailsManager(user, admin, librarian);
    }
}
