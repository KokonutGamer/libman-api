package org.example.libman;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails user = User.withUsername("user").password(encoder.encode("UserPW123#$")).roles("USER").build();
        UserDetails admin = User.withUsername("admin").password(encoder.encode("AdminPW123#$")).roles("ADMIN").build();
        UserDetails librarian = User.withUsername("librarian").password(encoder.encode("LibPW123#$")).roles("LIBRARIAN").build();
        return new InMemoryUserDetailsManager(user, admin, librarian);
    }
}
