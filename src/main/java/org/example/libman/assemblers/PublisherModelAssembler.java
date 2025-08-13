package org.example.libman.assemblers;

import org.example.libman.controllers.PublisherController;
import org.example.libman.entities.Publisher;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class PublisherModelAssembler implements RepresentationModelAssembler<Publisher, EntityModel<Publisher>> {
    @Override
    public @NonNull EntityModel<Publisher> toModel(@NonNull Publisher publisher) {
        return EntityModel.of(publisher,
                linkTo(methodOn(PublisherController.class).one(publisher.getId())).withSelfRel(),
                linkTo(methodOn(PublisherController.class).all()).withRel("publishers"));
    }
}
