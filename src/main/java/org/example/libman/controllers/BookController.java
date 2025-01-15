package org.example.libman.controllers;

import org.example.libman.entities.Book;
import org.example.libman.exceptions.BookNotFoundException;
import org.example.libman.repositories.BookRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

// Data returned by each method is written straight into the response body instead of rendering a template
@RestController
@RequestMapping("${api.endpoint}")
public class BookController {
    private final BookRepository repository;
    private final BookModelAssembler assembler;

    // BookRepository injected by constructor into the controller
    BookController(BookRepository repository, BookModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/books")
    CollectionModel<EntityModel<Book>> all() {
        List<EntityModel<Book>> books = repository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(books, linkTo(methodOn(BookController.class).all()).withSelfRel());
    }

    // TODO enforce authorization using Spring Security - LIBRARIAN and ADMIN
//    @Secured({"ROLE_ADMIN", "ROLE_LIBRARIAN"})
    @PostMapping("/books")
    ResponseEntity<?> newBook(@RequestBody Book newBook) {
        EntityModel<Book> entityModel = assembler.toModel(newBook);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    // TODO don't change this method; use it to subscribe users that want to hold a book
    @GetMapping("/books/{id}")
    EntityModel<Book> one(@PathVariable Long id) {
        Book book = repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        return assembler.toModel(book);
    }

    // TODO enforce authorization using Spring Security - LIBRARIAN and ADMIN
//    @Secured({"ROLE_ADMIN", "ROLE_LIBRARIAN"})
    @PutMapping("/books/{id}")
    ResponseEntity<?> replaceBook(@RequestBody Book newBook, @PathVariable Long id) {
        Book updatedBook = repository.findById(id).map(book -> {
            book.setTitle(newBook.getTitle());
            book.setAuthor(newBook.getAuthor());
            book.setVolume(newBook.getVolume());
            book.setEdition(newBook.getEdition());
            book.setPageCount(newBook.getPageCount());
            book.setPublicationDate(newBook.getPublicationDate());
            book.setNumberOfAvailableCopies(newBook.getNumberOfAvailableCopies());
            book.setTotalNumberOfCopies(newBook.getTotalNumberOfCopies());
            return repository.save(book);
        }).orElseGet(() -> {
            // If no id found, save the newBook into the repository
            return repository.save(newBook);
        });

        EntityModel<Book> entityModel = assembler.toModel(updatedBook);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    // TODO enforce authorization using Spring Security - USER and LIBRARIAN
//    @Secured({"ROLE_LIBRARIAN", "ROLE_USER"})
    @PatchMapping("/books/{id}/checkout")
    ResponseEntity<?> checkoutBook(@PathVariable Long id) {
        Book book = repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        int numberAvailable = book.getNumberOfAvailableCopies();
        if (numberAvailable > 0) {
            book.setNumberOfAvailableCopies(numberAvailable - 1);
            return ResponseEntity.ok(assembler.toModel(repository.save(book)));
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Method not allowed")
                        .withDetail("You can't checkout a book with " + book.getNumberOfAvailableCopies() + " copies available"));
    }

    // TODO enforce authorization using Spring Security - LIBRARIAN only
//    @Secured("ROLE_LIBRARIAN")
    @PatchMapping("/books/{id}/return")
    ResponseEntity<?> returnBook(@PathVariable Long id) {
        Book book = repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        int numberAvailable = book.getNumberOfAvailableCopies();
        int total = book.getTotalNumberOfCopies();
        if (numberAvailable < total) {
            book.setNumberOfAvailableCopies(numberAvailable + 1);
            return ResponseEntity.ok(assembler.toModel(repository.save(book)));
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Method not allowed")
                        .withDetail("You can't return a book with " + book.getNumberOfAvailableCopies() + " out of " + book.getTotalNumberOfCopies() + " copies available"));
    }

    // TODO enforce authorization using Spring Security
    @DeleteMapping("/books/{id}")
    ResponseEntity<?> deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
