package org.example.libman.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.example.libman.dtos.BookDTO;
import org.example.libman.entities.Book;
import org.example.libman.exceptions.BookNotFoundException;
import org.example.libman.repositories.BookRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// Data returned by each method is written straight into the response body instead of rendering a template
@RestController
public class BookController {

    private final BookRepository repository;
    private final BookModelAssembler assembler;

    // BookRepository injected by constructor into the controller
    public BookController(BookRepository repository, BookModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/books")
    public CollectionModel<EntityModel<Book>> all() {
        List<EntityModel<Book>> books = repository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(books, linkTo(methodOn(BookController.class).all()).withSelfRel());
    }

    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN')")
    @PostMapping("/books")
    public ResponseEntity<?> newBook(@RequestBody BookDTO bookDTO) {
        EntityModel<Book> entityModel = assembler.toModel(repository.save(Book.fromDTO(bookDTO)));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    // TODO don't change this method; use it to subscribe users that want to hold a book
    @GetMapping("/books/{id}")
    public EntityModel<Book> one(@PathVariable Long id) {
        Book book = repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        return assembler.toModel(book);
    }

    // TODO enforce authorization using Spring Security - LIBRARIAN and ADMIN
    @PutMapping("/books/{id}")
    public ResponseEntity<?> replaceBook(@RequestBody Book newBook, @PathVariable Long id) {
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
    @PatchMapping("/books/{id}/checkout")
    public ResponseEntity<?> checkoutBook(@PathVariable Long id) {
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
    @PatchMapping("/books/{id}/return")
    public ResponseEntity<?> returnBook(@PathVariable Long id) {
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
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
