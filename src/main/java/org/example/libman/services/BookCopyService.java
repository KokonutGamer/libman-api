package org.example.libman.services;

import java.util.List;

import org.example.libman.entities.BookCopy;
import org.example.libman.exceptions.CopyNotFoundException;
import org.example.libman.repositories.BookCopyRepository;
import org.example.libman.repositories.BookRepository;
import org.example.libman.repositories.LibraryLocationRepository;
import org.springframework.stereotype.Service;

@Service
public class BookCopyService {

    private final BookRepository bookRepository;
    private final BookCopyRepository bookCopyRepository;
    private final LibraryLocationRepository libraryLocationRepository;

    public BookCopyService(BookRepository bookRepository, BookCopyRepository bookCopyRepository,
            LibraryLocationRepository libraryLocationRepository) {
        this.bookRepository = bookRepository;
        this.bookCopyRepository = bookCopyRepository;
        this.libraryLocationRepository = libraryLocationRepository;
    }

    public BookCopy findByBarcode(String barcode) {
        return bookCopyRepository.findById(barcode).orElseThrow(() -> new CopyNotFoundException(barcode));
    }

    public List<BookCopy> findAllBookCopies() {
        return bookCopyRepository.findAll();
    }
}
