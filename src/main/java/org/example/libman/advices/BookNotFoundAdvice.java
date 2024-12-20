package org.example.libman.advices;

import org.example.libman.exceptions.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Signals that this advice is rendered straight into the response body
@RestControllerAdvice
public class BookNotFoundAdvice {
    @ExceptionHandler(BookNotFoundException.class)
    // Configures the advice to only respond when an BookNotFoundException is thrown
    @ResponseStatus(HttpStatus.NOT_FOUND)
    // Says to issue an HttpStatus.NOT_FOUND (404 error)
    String bookNotFoundHandler(BookNotFoundException exception) {
        return exception.getMessage();
    }
}
