package org.example.libman.authentication;

import org.example.libman.dtos.LoginUserDto;
import org.example.libman.dtos.RegisterUserDto;
import org.example.libman.entities.User;
import org.example.libman.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public User signup(RegisterUserDto input) {
        User user = new User(
                input.getUsername(),
                input.getEmail(),
                passwordEncoder.encode(input.getPassword()),
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis())
        );
        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        // TODO implement appropriate exception for this authentication method
        return userRepository.findByEmail(input.getEmail()).orElseThrow();
    }
}
