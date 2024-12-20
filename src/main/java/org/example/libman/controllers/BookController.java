package org.example.libman.controllers;

import org.example.libman.entities.Book;
import org.example.libman.exceptions.BookNotFoundException;
import org.example.libman.repositories.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Data returned by each method is written straight into the response body instead of rendering a template
@RestController
public class BookController {
    private final BookRepository repository;

    // BookRepository injected by constructor into the controller
    BookController(BookRepository repository) {
        this.repository = repository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/books")
    List<Book> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/books")
    Book newBook(@RequestBody Book newBook) {
        return repository.save(newBook);
    }

    // Single item
    @GetMapping("/books/{id}")
    Book one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    @PutMapping("/books/{id}")
    Book replaceBook(@RequestBody Book newBook, @PathVariable Long id) {
        // Find the repository by id
        return repository.findById(id).map(book -> {
            book.setTitle(newBook.getTitle());
            book.setAuthor(newBook.getAuthor());
            return repository.save(book);
        }).orElseGet(() -> {
            // If no id found, save the newBook into the repository
            return repository.save(newBook);
        });
    }

    @DeleteMapping("/books/{id}")
    void deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
