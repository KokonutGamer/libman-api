package org.example.libman.assemblers;

import org.example.libman.controllers.GenreController;
import org.example.libman.entities.Genre;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class GenreModelAssembler implements RepresentationModelAssembler<Genre, EntityModel<Genre>> {
    @Override
    public @NonNull EntityModel<Genre> toModel(@NonNull Genre genre) {
        return EntityModel.of(genre,
                linkTo(methodOn(GenreController.class).one(genre.getId())).withSelfRel(),
                linkTo(methodOn(GenreController.class).all()).withRel("genres"));
    }
}
