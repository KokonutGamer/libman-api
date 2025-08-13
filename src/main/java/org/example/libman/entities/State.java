package org.example.libman.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "state")
public class State {

    @Id
    @Column(length = 2, columnDefinition = "CHAR(2)")
    private String id;

    @Column(length = 13)
    private String name;

    // Relationships

    @OneToMany(mappedBy = "state")
    private Set<Customer> customersWithinState;
}
