package org.example.libman.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "last_name", length = 30)
    private String lastName;

    @Column(length = 40, nullable = true)
    private String email;

    @Column(length = 30)
    private String address;

    @Column(length = 20)
    private String city;

    @Column(name = "postal_code", length = 5, columnDefinition = "CHAR(5)")
    private String postalCode;

    // Relationships

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    @OneToMany(mappedBy = "customer")
    private Set<LibraryCard> ownedCards;

    @OneToMany(mappedBy = "customer")
    private Set<Phone> contactPhones;
}
