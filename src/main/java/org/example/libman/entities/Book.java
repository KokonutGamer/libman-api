package org.example.libman.entities;

import java.time.LocalDate;

import org.example.libman.dtos.BookDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

// Note that Spring Data also contains wrappers for MongoDB and Cassandra
// JPA annotation to make this object ready for storage in a JPA-based data store
@Entity
@Data
@NoArgsConstructor
public class Book {

    /* id, title, and author are attributes of our Book domain object
     * id is marked with more JPA annotations to indicate that it is
     * the primary key and is automatically populated by the JPA provider */
    private @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    private String title;

    private String author;

    private Integer volume;

    private Integer edition;

    private Integer pageCount;

    private LocalDate publicationDate;

    private Integer numberOfAvailableCopies;

    private Integer totalNumberOfCopies;

    public static Book fromDTO(BookDTO dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setVolume(dto.getVolume());
        book.setEdition(dto.getEdition());
        book.setPageCount(dto.getPageCount());
        book.setPublicationDate(dto.getPublicationDate());
        book.setNumberOfAvailableCopies(dto.getNumberOfAvailableCopies());
        book.setTotalNumberOfCopies(dto.getTotalNumberOfCopies());
        return book;
    }
}
