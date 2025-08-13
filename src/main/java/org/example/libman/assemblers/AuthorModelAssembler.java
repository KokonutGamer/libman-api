package org.example.libman.assemblers;

import org.example.libman.controllers.AuthorController;
import org.example.libman.entities.Author;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class AuthorModelAssembler implements RepresentationModelAssembler<Author, EntityModel<Author>> {
    @Override
    public @NonNull EntityModel<Author> toModel(@NonNull Author author) {
        return EntityModel.of(author, linkTo(methodOn(AuthorController.class).one(author.getId())).withSelfRel(),
                linkTo(methodOn(AuthorController.class).all()).withRel("authors"));
    }
}