package org.example.libman.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "book_copy")
public class BookCopy {

    private @Id
    @Column(length = 10, columnDefinition = "CHAR(10)")
    String barcode;

    // TODO change to FK reference
    private @Column(length = 13, columnDefinition = "CHAR(13)")
    String isbn;

    // TODO change to FK reference
    private Integer libraryLocationId;
}
