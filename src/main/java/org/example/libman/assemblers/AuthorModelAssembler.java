package org.example.libman.assemblers;

import java.util.List;
import java.util.stream.Collectors;

import org.example.libman.controllers.AuthorController;
import org.example.libman.controllers.BookController;
import org.example.libman.dtos.AuthorModel;
import org.example.libman.dtos.BookModel;
import org.example.libman.entities.Author;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.mediatype.hal.HalModelBuilder;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class AuthorModelAssembler implements RepresentationModelAssembler<Author, RepresentationModel<?>> {
    @Override
    public @NonNull RepresentationModel<?> toModel(@NonNull Author author) {
        AuthorModel model = AuthorModel.fromEntity(author);

        List<BookModel> booksEmbedded = author.getWrittenBooks().stream().map(b -> {
            BookModel bookModel = BookModel.simplified(b);
            bookModel.add(linkTo(methodOn(BookController.class).one(b.getIsbn())).withSelfRel());
            return bookModel;
        }).collect(Collectors.toList());

        RepresentationModel<?> halModel = HalModelBuilder.halModelOf(model)
                .embed(booksEmbedded)
                .link(linkTo(methodOn(AuthorController.class).one(author.getId())).withSelfRel())
                .link(linkTo(methodOn(AuthorController.class).all()).withRel("authors"))
                .build();

        return halModel;
    }
}