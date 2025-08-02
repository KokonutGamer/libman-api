package org.example.libman.entities;

import java.time.LocalDate;

import org.example.libman.dtos.BookDTO;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

// Note that Spring Data also contains wrappers for MongoDB and Cassandra
// JPA annotation to make this object ready for storage in a JPA-based data store
@Entity
@Data
@NoArgsConstructor
public class Book {

    private @Id
    @Column(length = 13, columnDefinition = "CHAR(13)")
    String isbn;

    private String title;

    private @Nullable
    String subtitle;

    private String author;

    private @Column(length = 4)
    @Nullable
    String volume;

    private @Column(length = 4)
    @Nullable
    String edition;

    private Integer pageCount;

    private LocalDate publicationDate;

    // TODO rewrite fromDTO method with new Book fields
    public static Book fromDTO(BookDTO dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setVolume(dto.getVolume());
        book.setEdition(dto.getEdition());
        book.setPageCount(dto.getPageCount());
        book.setPublicationDate(dto.getPublicationDate());
        return book;
    }
}
