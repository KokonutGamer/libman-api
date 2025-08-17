package org.example.libman.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = "booksWithinGenre")
@NoArgsConstructor
@Table(name = "genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(length = 30)
    private String name;
    
    // Relationships
    
    @ManyToMany
    @JoinTable(name = "book_to_genre", joinColumns = @JoinColumn(name = "genre_id"), inverseJoinColumns = @JoinColumn(name = "book_isbn", columnDefinition = "CHAR(13)"))
    private Set<Book> booksWithinGenre;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof Genre g) {
            return id != null && id.equals(g.getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
