package org.example.libman.exceptions;

public class CopyNotFoundException extends RuntimeException {
    public CopyNotFoundException(String barcode) {
        super("Could not find copy " + barcode);
    }
}
