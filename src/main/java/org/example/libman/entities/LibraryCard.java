package org.example.libman.entities;

import java.time.LocalDateTime;
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
@Table(name = "library_card")
public class LibraryCard {

    @Id
    @Column(length = 14, columnDefinition = "CHAR(14)")
    private String barcode;

    @Column(name = "issue_date")
    private LocalDateTime issueDate;

    @Column(name = "is_active")
    private Boolean isActive;

    // Relationships
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "checkoutCard")
    private Set<CheckoutRecord> checkoutHistory;
}
