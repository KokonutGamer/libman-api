package org.example.libman.entities;

import java.time.LocalDate;
import java.util.Set;

import org.example.libman.dtos.BookDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

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

    @JsonIgnore
    @ManyToMany(mappedBy = "writtenBooks")
    private Set<Author> authors;

    @JsonIgnore
    @ManyToMany(mappedBy = "booksWithinGenre")
    private Set<Genre> genres;

    @JsonIgnore
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if(obj instanceof Book b) {
            return isbn != null && isbn.equals(b.getIsbn());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }
}
