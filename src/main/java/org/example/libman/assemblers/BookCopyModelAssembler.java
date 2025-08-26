package org.example.libman.assemblers;

import org.example.libman.dtos.BookCopyModel;
import org.example.libman.dtos.BookModel;
import org.example.libman.entities.BookCopy;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.mediatype.hal.HalModelBuilder;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class BookCopyModelAssembler implements RepresentationModelAssembler<BookCopy, RepresentationModel<?>> {

    @Override
    public RepresentationModel<?> toModel(BookCopy entity) {
        BookCopyModel model = BookCopyModel.fromEntity(entity);

        // Book
        BookModel bookModel = BookModel.simplified(entity.getCatalogBook());

        // TODO Library Location

        // TODO Checkout Records

        RepresentationModel<?> halModel = HalModelBuilder.halModelOf(model)
                .embed(bookModel)
                .build();
        return halModel;
    }

}
