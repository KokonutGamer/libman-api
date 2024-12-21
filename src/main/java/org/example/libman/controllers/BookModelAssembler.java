package org.example.libman.controllers;

import org.example.libman.entities.Book;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class BookModelAssembler implements RepresentationModelAssembler<Book, EntityModel<Book>> {
    @Override
    public EntityModel<Book> toModel(Book book) {
        return EntityModel.of(book, linkTo(methodOn(BookController.class).one(book.getId())).withSelfRel(), linkTo(methodOn(BookController.class).all()).withRel("books"));
    }
}
