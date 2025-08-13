package org.example.libman.entities;

import java.time.LocalDate;
import java.util.Set;

import org.example.libman.dtos.BookDTO;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// Note that Spring Data also contains wrappers for MongoDB and Cassandra
// JPA annotation to make this object ready for storage in a JPA-based data store
@Entity
@Getter
@Setter
@ToString(exclude = { "publisher", "authors", "genres", "libraryCopies" })
@NoArgsConstructor
@Table(name = "book")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "isbn")
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

    @ManyToMany(mappedBy = "writtenBooks")
    private Set<Author> authors;

    @ManyToMany(mappedBy = "booksWithinGenre")
    private Set<Genre> genres;

    @OneToMany(mappedBy = "catalogBook")
    private Set<BookCopy> libraryCopies;

    public static Book fromDTO(BookDTO dto) {
        Book book = new Book();
        book.setIsbn(dto.getIsbn());
        book.setTitle(dto.getTitle());

        if (dto.getSubtitile() != null && !dto.getSubtitile().isBlank()) {
            book.setSubtitle(dto.getSubtitile());
        }

        if (dto.getVolume() != null) {
            book.setVolume(dto.getVolume());
        }

        if (dto.getEdition() != null) {
            book.setEdition(dto.getEdition());
        }

        book.setPageCount(dto.getPageCount());
        book.setPublicationDate(dto.getPublicationDate());
        book.setDescription(dto.getDescription());
        book.setDdn(dto.getDdn());
        return book;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof Book b) {
            return isbn != null && isbn.equals(b.getIsbn());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }
}
