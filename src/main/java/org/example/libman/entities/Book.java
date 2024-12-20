package org.example.libman.entities;

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

    public Book() {
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
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
