package org.example.libman.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String isbn) {
        super("Could not find book " + isbn);
    }
}
