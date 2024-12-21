package org.example.libman.entities;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

// Note that Spring Data also contains wrappers for MongoDB and Cassandra

// JPA annotation to make this object ready for storage in a JPA-based data store
@Entity
public class Book {

    /* id, title, and author are attributes of our Book domain object
     * id is marked with more JPA annotations to indicate that it is
     * the primary key and is automatically populated by the JPA provider */
    private @Id
    @GeneratedValue Long id;
    private String title;
    private String author;
    private Integer volume;
    private Integer edition;
    private Integer pageCount;
    private LocalDate publicationDate;
    private BookStatus status;

    public Book() {
    }

    public Book(String title, String author, Integer volume, Integer edition, Integer pageCount, LocalDate publicationDate, BookStatus status) {
        this.title = title;
        this.author = author;
        this.volume = volume;
        this.edition = edition;
        this.pageCount = pageCount;
        this.publicationDate = publicationDate;
        this.status = status;
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public Integer getVolume() {
        return volume;
    }

    public Integer getEdition() {
        return edition;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Book)) {
            return false;
        }

        Book book = (Book) object;
        return Objects.equals(this.id, book.id) && Objects.equals(this.title, book.title) && Objects.equals(this.author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.title, this.author);
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + this.id + ", title=" + this.title + ", author=" + this.author + '}';
    }
}
