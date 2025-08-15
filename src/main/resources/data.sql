-- Start publishers
INSERT INTO Publisher(name)
    VALUES ('Square Enix Manga & Books');

INSERT INTO Publisher(name)
    VALUES ('Viz Media');

-- End publishers
-- Start authors
INSERT INTO Author(first_name, last_name)
    VALUES ('Natsu', 'Hyuuga');

INSERT INTO Author(first_name, last_name)
    VALUES ('Nekokurage', NULL);

INSERT INTO Author(first_name, last_name)
    VALUES ('Tsukasa', 'Abe');

-- End authors
-- Start genres
INSERT INTO Genre(name)
    VALUES ('Fantasy');

INSERT INTO Genre(name)
    VALUES ('Science Fiction');

INSERT INTO Genre(name)
    VALUES ('Mystery');

INSERT INTO Genre(name)
    VALUES ('Romance');

INSERT INTO Genre(name)
    VALUES ('Historical Fiction');

INSERT INTO Genre(name)
    VALUES ('Horror');

INSERT INTO Genre(name)
    VALUES ('Non-Fiction');

INSERT INTO Genre(name)
    VALUES ('Young Adult');

INSERT INTO Genre(name)
    VALUES ('Adventure');

INSERT INTO Genre(name)
    VALUES ('Slice of Life');

INSERT INTO Genre(name)
    VALUES ('Detective Fiction');

INSERT INTO Genre(name)
    VALUES ('Drama');

INSERT INTO Genre(name)
    VALUES ('Comedy');

INSERT INTO Genre(name)
    VALUES ('Medical');

INSERT INTO Genre(name)
    VALUES ('Thriller');

-- End genres
-- Start books
INSERT INTO Book(isbn, title, volume, page_count, publication_date, ddn, publisher_id)
    VALUES ('9781646090709', 'The Apothecary Diaries', '1', 178, '2020-12-08', '741.5952', 1);

INSERT INTO Book(isbn, title, volume, page_count, publication_date, ddn, publisher_id)
    VALUES ('9781974725762', 'Frieren: Beyond Journey''s End', '1', 192, '2021-11-09', '741.5952', 2);

-- End books
-- Start Apothecary authors
INSERT INTO Book_To_Author(book_isbn, author_id)
    VALUES ('9781646090709', 1);

INSERT INTO Book_To_Author(book_isbn, author_id)
    VALUES ('9781646090709', 2);

-- End Apothecary authors
-- Start Frieren authors
INSERT INTO Book_To_Author(book_isbn, author_id)
    VALUES ('9781974725762', 3);

-- End Frieren authors
-- Start Apothecary genres
INSERT INTO Book_To_Genre(book_isbn, genre_id)
    VALUES ('9781646090709',
        SELECT
            id
        FROM
            Genre
        WHERE
            name = 'Comedy');

INSERT INTO Book_To_Genre(book_isbn, genre_id)
    VALUES ('9781646090709',
        SELECT
            id
        FROM
            Genre
        WHERE
            name = 'Drama');

INSERT INTO Book_To_Genre(book_isbn, genre_id)
    VALUES ('9781646090709',
        SELECT
            id
        FROM
            Genre
        WHERE
            name = 'Fantasy');

INSERT INTO Book_To_Genre(book_isbn, genre_id)
    VALUES ('9781646090709',
        SELECT
            id
        FROM
            Genre
        WHERE
            name = 'Historical Fiction');

INSERT INTO Book_To_Genre(book_isbn, genre_id)
    VALUES ('9781646090709',
        SELECT
            id
        FROM
            Genre
        WHERE
            name = 'Medical');

INSERT INTO Book_To_Genre(book_isbn, genre_id)
    VALUES ('9781646090709',
        SELECT
            id
        FROM
            Genre
        WHERE
            name = 'Mystery');

INSERT INTO Book_To_Genre(book_isbn, genre_id)
    VALUES ('9781646090709',
        SELECT
            id
        FROM
            Genre
        WHERE
            name = 'Romance');

INSERT INTO Book_To_Genre(book_isbn, genre_id)
    VALUES ('9781646090709',
        SELECT
            id
        FROM
            Genre
        WHERE
            name = 'Slice of Life');

INSERT INTO Book_To_Genre(book_isbn, genre_id)
    VALUES ('9781646090709',
        SELECT
            id
        FROM
            Genre
        WHERE
            name = 'Thriller');

-- End Apothecary genres
INSERT INTO Book_To_Genre(book_isbn, genre_id)
    VALUES ('9781974725762',
        SELECT
            id
        FROM
            Genre
        WHERE
            name = 'Fantasy');

INSERT INTO Book_To_Genre(book_isbn, genre_id)
    VALUES ('9781974725762',
        SELECT
            id
        FROM
            Genre
        WHERE
            name = 'Drama');

INSERT INTO Book_To_Genre(book_isbn, genre_id)
    VALUES ('9781974725762',
        SELECT
            id
        FROM
            Genre
        WHERE
            name = 'Adventure');

