package org.example.libman.controllers;

import org.example.libman.entities.Book;
import org.example.libman.exceptions.BookNotFoundException;
import org.example.libman.repositories.BookRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

// Data returned by each method is written straight into the response body instead of rendering a template
@RestController
public class BookController {
    private final BookRepository repository;
    private final BookModelAssembler assembler;

    // BookRepository injected by constructor into the controller
    BookController(BookRepository repository, BookModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // Aggregate root now as a resource
    // CollectionModel encapsulates collections of resources (in this case, book RESOURCES) instead of a single resource entity
    // Includes links
    @GetMapping("/books")
    CollectionModel<EntityModel<Book>> all() {
        List<EntityModel<Book>> books = repository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(books, linkTo(methodOn(BookController.class).all()).withSelfRel());
    }

    @PostMapping("/books")
    ResponseEntity<?> newBook(@RequestBody Book newBook) {
        EntityModel<Book> entityModel = assembler.toModel(newBook);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    // Changed to EntityModel<Book>
    // Wraps data and collection of links
    @GetMapping("/books/{id}")
    EntityModel<Book> one(@PathVariable Long id) {
        Book book = repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        return assembler.toModel(book);
    }

    @PutMapping("/books/{id}")
    ResponseEntity<?> replaceBook(@RequestBody Book newBook, @PathVariable Long id) {
        Book updatedBook = repository.findById(id).map(book -> {
            book.setTitle(newBook.getTitle());
            book.setAuthor(newBook.getAuthor());
            book.setVolume(newBook.getVolume());
            book.setEdition(newBook.getEdition());
            book.setPageCount(newBook.getPageCount());
            book.setPublicationDate(newBook.getPublicationDate());
            book.setStatus(newBook.getStatus());
            return repository.save(book);
        }).orElseGet(() -> {
            // If no id found, save the newBook into the repository
            return repository.save(newBook);
        });

        EntityModel<Book> entityModel = assembler.toModel(updatedBook);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping("/books/{id}")
    ResponseEntity<?> deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
