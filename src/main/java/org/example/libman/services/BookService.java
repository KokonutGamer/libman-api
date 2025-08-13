package org.example.libman.services;

import java.util.HashSet;
import java.util.List;

import org.example.libman.dtos.AuthorDTO;
import org.example.libman.dtos.BookDTO;
import org.example.libman.entities.Author;
import org.example.libman.entities.Book;
import org.example.libman.entities.Genre;
import org.example.libman.entities.Publisher;
import org.example.libman.exceptions.BookNotFoundException;
import org.example.libman.repositories.AuthorRepository;
import org.example.libman.repositories.BookRepository;
import org.example.libman.repositories.GenreRepository;
import org.example.libman.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final PublisherRepository publisherRepository;

    public BookService(AuthorRepository authorRepository, BookRepository bookRepository,
            GenreRepository genreRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.publisherRepository = publisherRepository;
    }

    public Book saveBook(BookDTO bookDTO) {
        // TODO refactor to check if the given book already exists

        Book book = Book.fromDTO(bookDTO);

        // TODO refactor to check if the given publisher already exists
        Publisher publisher = new Publisher();
        publisher.setName(bookDTO.getPublisherName());
        publisher = publisherRepository.save(publisher);
        book.setPublisher(publisher);

        // TODO refactor to check if the given author already exists
        book.setAuthors(new HashSet<>());
        for (AuthorDTO authorDTO : bookDTO.getAuthors()) {
            Author author = authorRepository.save(Author.fromDTO(authorDTO));
            book.getAuthors().add(author);
        }

        // TODO refactor to check if the given genre already exists
        book.setGenres(new HashSet<>());
        for (String genreName : bookDTO.getGenres()) {
            Genre genre = new Genre();
            genre.setName(genreName);
            genre = genreRepository.save(genre);
            book.getGenres().add(genre);
        }

        bookRepository.save(book);
        return book;
    }

    public Book findByIsbn(String isbn) {
        return bookRepository.findById(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }
}