package org.example.libman.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.example.libman.assemblers.BookModelAssembler;
import org.example.libman.dtos.BookDTO;
import org.example.libman.entities.Book;
import org.example.libman.services.BookService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.RepresentationModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    private final BookModelAssembler bookAssembler;

    // BookRepository injected by constructor into the controller
    public BookController(BookService bookService, BookModelAssembler assembler) {
        this.bookService = bookService;
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
    public ResponseEntity<?> replaceBook(@RequestBody BookDTO bookDTO, @PathVariable String isbn) {
        boolean bookExists = bookService.checkBookExistsByIsbn(bookDTO.getIsbn());
        Book updatedBook = bookService.updateBook(bookDTO);
        RepresentationModel<?> entityModel = bookAssembler.toModel(updatedBook);
        return (bookExists) ? ResponseEntity.ok(entityModel)
                : ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN')")
    @DeleteMapping("/books/{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable String isbn) {
        bookService.deleteBookByIsbn(isbn);
        return ResponseEntity.noContent().build();
    }
}
