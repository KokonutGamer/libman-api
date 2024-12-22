package org.example.libman.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

// Note that Spring Data also contains wrappers for MongoDB and Cassandra

// JPA annotation to make this object ready for storage in a JPA-based data store
@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Book {

    /* id, title, and author are attributes of our Book domain object
     * id is marked with more JPA annotations to indicate that it is
     * the primary key and is automatically populated by the JPA provider */
    private @Id
    @GeneratedValue(strategy = GenerationType.AUTO) Long id;
    @NonNull private String title;
    @NonNull private String author;
    @NonNull private Integer volume;
    @NonNull private Integer edition;
    @NonNull private Integer pageCount;
    @NonNull private LocalDate publicationDate;
    @NonNull private BookStatus status;
}
