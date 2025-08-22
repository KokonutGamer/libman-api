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
import org.springframework.data.domain.Example;
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
        if (bookRepository.existsById(bookDTO.getIsbn())) {
            return bookRepository.getReferenceById(bookDTO.getIsbn());
        }

        Book book = Book.fromDTO(bookDTO);

        Publisher publisherProbe = new Publisher();
        publisherProbe.setName(bookDTO.getPublisherName());

        publisherRepository.findOne(Example.of(publisherProbe)).ifPresentOrElse(p -> {
            p.getPublishedBooks().add(book);
            book.setPublisher(p);
        }, () -> {
            Publisher publisher = new Publisher();
            publisher.setName(bookDTO.getPublisherName());
            publisher = publisherRepository.save(publisher);
            publisher.setPublishedBooks(new HashSet<>());
            publisher.getPublishedBooks().add(book);
            book.setPublisher(publisher);
        });

        book.setAuthors(new HashSet<>());
        for (AuthorDTO authorDTO : bookDTO.getAuthors()) {
            Author authorProbe = Author.fromDTO(authorDTO);
            authorRepository.findOne(Example.of(authorProbe)).ifPresentOrElse(a -> {
                a.getWrittenBooks().add(book);
                book.getAuthors().add(a);
            }, () -> {
                Author author = authorRepository.save(Author.fromDTO(authorDTO));
                book.getAuthors().add(author);
            });

        }

        book.setGenres(new HashSet<>());
        for (String genreName : bookDTO.getGenres()) {
            Genre genreProbe = new Genre();
            genreProbe.setName(genreName);

            genreRepository.findOne(Example.of(genreProbe)).ifPresentOrElse(g -> {
                g.getBooksWithinGenre().add(book);
                book.getGenres().add(g);
            }, () -> {
                Genre genre = new Genre();
                genre.setName(genreName);
                genre = genreRepository.save(genre);
                book.getGenres().add(genre);
            });

        }

        bookRepository.save(book);
        return book;
    }

    public boolean checkBookExistsByIsbn(String isbn) {
        return bookRepository.existsById(isbn);
    }

    public void deleteBookByIsbn(String isbn) {
        bookRepository.deleteById(isbn);
    }

    public Book findByIsbn(String isbn) {
        return bookRepository.findById(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book updateBook(BookDTO dto) {
        return bookRepository.findById(dto.getIsbn()).map(book -> {
            book.setIsbn(dto.getIsbn());
            book.setTitle(dto.getTitle());
            book.setSubtitle(dto.getSubtitle());
            book.setVolume(dto.getVolume());
            book.setEdition(dto.getEdition());
            book.setPageCount(dto.getPageCount());
            book.setPublicationDate(dto.getPublicationDate());
            book.setDescription(dto.getDescription());
            book.setDdn(dto.getDdn());
            return book;
        }).orElseGet(() -> {
            return saveBook(dto);
        });
    }
}