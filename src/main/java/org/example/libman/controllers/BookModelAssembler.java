package org.example.libman.controllers;

import org.example.libman.entities.Book;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class BookModelAssembler implements RepresentationModelAssembler<Book, EntityModel<Book>> {
    @Override
    public @NonNull EntityModel<Book> toModel(@NonNull Book book) {
        return EntityModel.of(book, linkTo(methodOn(BookController.class).one(book.getId())).withSelfRel(), linkTo(methodOn(BookController.class).all()).withRel("books"));
    }
}
