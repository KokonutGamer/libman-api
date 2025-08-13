package org.example.libman.exceptions;

public class PublisherNotFoundException extends RuntimeException {
    public PublisherNotFoundException(Integer id) {
        super("Could not find publisher with id " + id);
    }
}
