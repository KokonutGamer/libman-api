package org.example.libman.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "book_copy")
public class BookCopy {

    @Id
    @Column(length = 14, columnDefinition = "CHAR(14)")
    private String barcode;

    // Relationships

    @ManyToOne
    @JoinColumn(name = "isbn", nullable = false)
    private Book catalogBook;

    @ManyToOne
    @JoinColumn(name = "library_location_id", nullable = false)
    private LibraryLocation location;

    @OneToMany(mappedBy="checkedOutBook")
    private Set<CheckoutRecord> checkedOutRecords;
}
