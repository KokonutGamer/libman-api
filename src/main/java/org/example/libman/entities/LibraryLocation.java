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
@Table(name = "library_location")
public class LibraryLocation {

    private @Id
    Integer id;

    private @Column(length = 30)
    String name;
}
