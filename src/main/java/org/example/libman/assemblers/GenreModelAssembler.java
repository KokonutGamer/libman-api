package org.example.libman.assemblers;

import java.util.List;
import java.util.stream.Collectors;

import org.example.libman.controllers.BookController;
import org.example.libman.controllers.GenreController;
import org.example.libman.dtos.BookModel;
import org.example.libman.dtos.GenreModel;
import org.example.libman.entities.Genre;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.mediatype.hal.HalModelBuilder;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class GenreModelAssembler implements RepresentationModelAssembler<Genre, RepresentationModel<?>> {
    @Override
    public @NonNull RepresentationModel<?> toModel(@NonNull Genre genre) {
        GenreModel model = GenreModel.fromEntity(genre);

        List<BookModel> booksEmbedded = genre.getBooksWithinGenre().stream().map(b -> {
            BookModel bookModel = BookModel.simplified(b);
            bookModel.add(linkTo(methodOn(BookController.class).one(b.getIsbn())).withSelfRel());
            return bookModel;
        }).collect(Collectors.toList());

        RepresentationModel<?> halModel = HalModelBuilder.halModelOf(model)
                .embed(booksEmbedded)
                .link(linkTo(methodOn(GenreController.class).one(genre.getId())).withSelfRel())
                .link(linkTo(methodOn(GenreController.class).all()).withRel("genres"))
                .build();

        return halModel;
    }
}
