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

import jakarta.validation.Valid;

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
    public ResponseEntity<?> newBook(@Valid @RequestBody BookDTO bookDTO) {
        EntityModel<Book> entityModel = assembler.toModel(repository.save(Book.fromDTO(bookDTO)));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    // TODO don't change this method; use it to subscribe users that want to hold a book
    @GetMapping("/books/{isbn}")
    public EntityModel<Book> one(@PathVariable String isbn) {
        Book book = repository.findById(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
        return assembler.toModel(book);
    } 

    // TODO enforce authorization using Spring Security - LIBRARIAN and ADMIN
    @PutMapping("/books/{isbn}")
    public ResponseEntity<?> replaceBook(@RequestBody Book newBook, @PathVariable String isbn) {
        Book updatedBook = repository.findById(isbn).map(book -> {
            book.setTitle(newBook.getTitle());
            book.setVolume(newBook.getVolume());
            book.setEdition(newBook.getEdition());
            book.setPageCount(newBook.getPageCount());
            book.setPublicationDate(newBook.getPublicationDate());
            return repository.save(book);
        }).orElseGet(() -> {
            // If no id found, save the newBook into the repository
            return repository.save(newBook);
        });

        EntityModel<Book> entityModel = assembler.toModel(updatedBook);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    // TODO enforce authorization using Spring Security - USER and LIBRARIAN
    @PatchMapping("/books/{isbn}/checkout")
    public ResponseEntity<?> checkoutBook(@PathVariable String isbn) {
        Book book = repository.findById(isbn).orElseThrow(() -> new BookNotFoundException(isbn));

        // TODO refactor checkout logic, or delegate to a service

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Method not allowed"));
    }

    // TODO enforce authorization using Spring Security - LIBRARIAN only
    @PatchMapping("/books/{isbn}/return")
    public ResponseEntity<?> returnBook(@PathVariable String isbn) {
        Book book = repository.findById(isbn).orElseThrow(() -> new BookNotFoundException(isbn));

        // TODO refactor return logic, or delegate to a service

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Method not allowed"));
    }

    // TODO enforce authorization using Spring Security
    @DeleteMapping("/books/{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable String isbn) {
        repository.deleteById(isbn);
        return ResponseEntity.noContent().build();
    }
}
