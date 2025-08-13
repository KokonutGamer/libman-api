package org.example.libman.exceptions;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(Integer id) {
        super("Could not find book with id " + id);
    }
}
