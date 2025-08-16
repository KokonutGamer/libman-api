package org.example.libman.assemblers;

import java.util.List;
import java.util.stream.Collectors;

import org.example.libman.controllers.BookController;
import org.example.libman.controllers.PublisherController;
import org.example.libman.dtos.BookModel;
import org.example.libman.dtos.PublisherModel;
import org.example.libman.entities.Publisher;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.mediatype.hal.HalModelBuilder;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class PublisherModelAssembler implements RepresentationModelAssembler<Publisher, RepresentationModel<?>> {
    @Override
    public @NonNull RepresentationModel<?> toModel(@NonNull Publisher publisher) {
        PublisherModel model = PublisherModel.fromEntity(publisher);

        List<BookModel> booksEmbedded = publisher.getPublishedBooks().stream().map(b -> {
            BookModel bookModel = BookModel.fromEntity(b);
            bookModel.add(linkTo(methodOn(BookController.class).one(b.getIsbn())).withSelfRel());
            return bookModel;
        }).collect(Collectors.toList());

        RepresentationModel<?> halModel = HalModelBuilder.halModelOf(model)
                .embed(booksEmbedded)
                .link(linkTo(methodOn(PublisherController.class).one(publisher.getId())).withSelfRel())
                .link(linkTo(methodOn(PublisherController.class).all()).withRel("publishers"))
                .build();

        return halModel;
    }
}
