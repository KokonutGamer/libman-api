package org.example.libman.repositories;

import org.example.libman.entities.LibraryLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryLocationRepository extends JpaRepository<LibraryLocation, Integer> {}
