package org.example.libman.repositories;

import org.example.libman.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {}
