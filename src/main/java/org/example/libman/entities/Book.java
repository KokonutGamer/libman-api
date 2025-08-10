package org.example.libman.entities;

import java.time.LocalDate;
import java.util.Set;

import org.example.libman.dtos.BookDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

// Note that Spring Data also contains wrappers for MongoDB and Cassandra
// JPA annotation to make this object ready for storage in a JPA-based data store
@Entity
@Data
@NoArgsConstructor
public class Book {

    @Id
    @Column(length = 13, columnDefinition = "CHAR(13)")
    private String isbn;

    private String title;

    @Column(nullable = true)
    private String subtitle;

    @Column(length = 4, nullable = true)
    private String volume;

    @Column(length = 4, nullable = true)
    private String edition;

    @Column(name = "page_count")
    private Integer pageCount;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    private String description;

    @Column(length = 15)
    private String ddn;

    // Relationships

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    @ManyToMany
    private Set<Author> authors;

    @ManyToMany
    private Set<Genre> genres;

    @OneToMany(mappedBy = "catalogBook")
    private Set<BookCopy> libraryCopies;

    // TODO rewrite fromDTO method with new Book fields
    public static Book fromDTO(BookDTO dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setVolume(dto.getVolume());
        book.setEdition(dto.getEdition());
        book.setPageCount(dto.getPageCount());
        book.setPublicationDate(dto.getPublicationDate());
        return book;
    }
}
