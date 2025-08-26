package org.example.libman.repositories;

import org.example.libman.entities.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCopyRepository extends JpaRepository<BookCopy, String> {}
