package org.example.libman.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.example.libman.assemblers.BookModelAssembler;
import org.example.libman.dtos.BookDTO;
import org.example.libman.dtos.BookModel;
import org.example.libman.entities.Book;
import org.example.libman.exceptions.BookNotFoundException;
import org.example.libman.repositories.BookRepository;
import org.example.libman.services.BookService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.RepresentationModel;
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

    private final BookService bookService;
    private final BookRepository bookRepository;
    private final BookModelAssembler bookAssembler;

    // BookRepository injected by constructor into the controller
    public BookController(BookService bookService, BookRepository repository, BookModelAssembler assembler) {
        this.bookService = bookService;
        this.bookRepository = repository;
        this.bookAssembler = assembler;
    }

    // Public endpoints 

    @GetMapping("/books")
    public CollectionModel<RepresentationModel<?>> all() {
        List<RepresentationModel<?>> books = bookService.findAllBooks().stream().map(bookAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(books, linkTo(methodOn(BookController.class).all()).withSelfRel());
    }

    @GetMapping("/books/{isbn}")
    public RepresentationModel<?> one(@PathVariable String isbn) {
        Book book = bookService.findByIsbn(isbn);
        return bookAssembler.toModel(book);
    }

    // Auth endpoints

    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN')")
    @PostMapping("/books")
    public ResponseEntity<?> newBook(@Valid @RequestBody BookDTO bookDTO) {
        Book newBook = bookService.saveBook(bookDTO);

        RepresentationModel<?> entityModel = bookAssembler.toModel(newBook);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN')")
    @PutMapping("/books/{isbn}")
    public ResponseEntity<?> replaceBook(@RequestBody Book newBook, @PathVariable String isbn) {
        Book updatedBook = bookRepository.findById(isbn).map(book -> {
            book.setTitle(newBook.getTitle());
            book.setVolume(newBook.getVolume());
            book.setEdition(newBook.getEdition());
            book.setPageCount(newBook.getPageCount());
            book.setPublicationDate(newBook.getPublicationDate());
            return bookRepository.save(book);
        }).orElseGet(() -> {
            // If no id found, save the newBook into the repository
            return bookRepository.save(newBook);
        });

        RepresentationModel<?> entityModel = bookAssembler.toModel(updatedBook);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN')")
    @PatchMapping("/books/{isbn}/checkout")
    public ResponseEntity<?> checkoutBook(@PathVariable String isbn) {
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new BookNotFoundException(isbn));

        // TODO refactor checkout logic, or delegate to a service

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Method not allowed"));
    }

    @PreAuthorize("hasRole('LIBRARIAN')")
    @PatchMapping("/books/{isbn}/return")
    public ResponseEntity<?> returnBook(@PathVariable String isbn) {
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new BookNotFoundException(isbn));

        // TODO refactor return logic, or delegate to a service

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Method not allowed"));
    }

    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN')")
    @DeleteMapping("/books/{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable String isbn) {
        bookRepository.deleteById(isbn);
        return ResponseEntity.noContent().build();
    }
}
