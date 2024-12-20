package org.example.libman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// Meta-annotation that pulls in component scanning, autoconfiguration, and property support
// Starts a servlet container and serves up our service
@SpringBootApplication
public class LibmanApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibmanApiApplication.class, args);
    }

}
