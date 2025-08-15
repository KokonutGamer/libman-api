package org.example.libman.assemblers;

import java.util.List;
import java.util.stream.Collectors;

import org.example.libman.controllers.AuthorController;
import org.example.libman.controllers.BookController;
import org.example.libman.dtos.AuthorModel;
import org.example.libman.dtos.BookModel;
import org.example.libman.entities.Book;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.mediatype.hal.HalModelBuilder;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class BookModelAssembler implements RepresentationModelAssembler<Book, RepresentationModel<?>> {

    @Override
    public @NonNull RepresentationModel<?> toModel(@NonNull Book book) {
        BookModel model = BookModel.fromEntity(book);

        // Authors
        List<AuthorModel> authorsEmbedded = book.getAuthors().stream().map(a -> {
            AuthorModel authorModel = AuthorModel.fromEntity(a);
            authorModel.add(linkTo(methodOn(AuthorController.class).one(a.getId())).withSelfRel());
            return authorModel;
        }).collect(Collectors.toList());

        RepresentationModel<?> halModel = HalModelBuilder.halModelOf(model)
                .embed(authorsEmbedded)
                .link(linkTo(methodOn(BookController.class).one(book.getIsbn())).withSelfRel())
                .link(linkTo(methodOn(BookController.class).all()).withRel("books"))
                .build();

        return halModel;
    }
}