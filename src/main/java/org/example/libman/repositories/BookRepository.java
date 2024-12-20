package org.example.libman.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.libman.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
