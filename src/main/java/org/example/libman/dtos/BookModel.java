package org.example.libman.dtos;

import java.time.LocalDate;

import org.example.libman.entities.Book;
import org.springframework.hateoas.RepresentationModel;
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
@Relation(itemRelation = "book", collectionRelation = "books")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BookModel extends RepresentationModel<BookModel> {

    @EqualsAndHashCode.Include
    private String isbn;

    private String title;
    private String subtitle;
    private String volume;
    private String edition;
    private Integer pageCount;
    private LocalDate publicationDate;
    private String description;
    private String ddn;

    public static BookModel fromEntity(Book book) {
        BookModel model = new BookModel();
        model.setIsbn(book.getIsbn());
        model.setTitle(book.getTitle());
        model.setSubtitle(book.getSubtitle());
        model.setVolume(book.getVolume());
        model.setEdition(book.getEdition());
        model.setPageCount(book.getPageCount());
        model.setPublicationDate(book.getPublicationDate());
        model.setDescription(book.getDescription());
        model.setDdn(book.getDdn());
        return model;
    }
}
