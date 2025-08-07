package org.example.libman;

import java.time.LocalDate;

import org.example.libman.entities.Book;
import org.example.libman.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    // Spring Boot runs ALL CommandLineRunner beans once the application context is
    // loaded
    @Bean
    public CommandLineRunner initDatabase(BookRepository repository) {
        // Requests a copy of the repository
        String logText = "Preloading {}";

        // Create books
        Book apothecary = new Book();
        apothecary.setIsbn("9781646090709");
        apothecary.setTitle("The Apothecary Diaries: Volume 1");
        apothecary.setVolume("1");
        apothecary.setPageCount(178);
        apothecary.setPublicationDate(LocalDate.of(2020, 12, 8));

        Book frieren = new Book();
        frieren.setIsbn("9781974725762");
        frieren.setTitle("Frieren: Beyond Journey's End");
        frieren.setVolume("1");
        frieren.setPageCount(192);
        frieren.setPublicationDate(LocalDate.of(2021, 11, 9));

        return args -> {
            // Creates two Book entities to store into the repository
            log.info(logText, repository.save(apothecary));
            log.info(logText, repository.save(frieren));
        };
    }

    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails user = User.withUsername("user").password(encoder.encode("UserPW123#$")).roles("USER").build();
        UserDetails admin = User.withUsername("admin").password(encoder.encode("AdminPW123#$")).roles("ADMIN").build();
        UserDetails librarian = User.withUsername("librarian").password(encoder.encode("LibPW123#$")).roles("LIBRARIAN")
                .build();
        return new InMemoryUserDetailsManager(user, admin, librarian);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for API
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated())
                .httpBasic(httpBasic -> {
                });
        return http.build();
    }
}
