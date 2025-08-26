package org.example.libman.dtos;

import org.example.libman.entities.BookCopy;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Relation(itemRelation = "copy", collectionRelation = "copies")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BookCopyModel {

    @EqualsAndHashCode.Include
    private String barcode;

    public static BookCopyModel fromEntity(BookCopy bookCopy) {
        BookCopyModel model = new BookCopyModel();
        model.setBarcode(bookCopy.getBarcode());
        return model;
    }
}
