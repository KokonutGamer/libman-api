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
@Table(name = "phone_type")
public class PhoneType {

    @Id
    @Column(length = 2, columnDefinition = "CHAR(2)")
    private String id;

    @Column(length = 15)
    private String name;

    // Relationships

    @OneToMany(mappedBy = "phoneType")
    private Set<Phone> phones;
}
