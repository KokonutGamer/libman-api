package org.example.libman.entities;

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
@Table(name = "phone")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 10)
    private String number;

    @Column(length = 5, nullable = true)
    private String extension;

    // Relationships

    @ManyToOne
    @JoinColumn(name = "phone_type_id", columnDefinition = "CHAR(2)")
    private PhoneType phoneType;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
