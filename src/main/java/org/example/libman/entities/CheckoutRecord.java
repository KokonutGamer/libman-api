package org.example.libman.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "checkout_record")
public class CheckoutRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime date;

    @Column(name = "check-in_due")
    private LocalDateTime checkInDue;

    @Column(name = "check-in_date", nullable = true)
    private LocalDateTime checkInDate;

    // Relationships
    @ManyToOne
    @JoinColumn(name = "library_card_barcode", nullable = false)
    private LibraryCard checkoutCard;

    @ManyToOne
    @JoinColumn(name = "book_copy_barcode", nullable = false)
    private BookCopy checkedOutBook;
}
