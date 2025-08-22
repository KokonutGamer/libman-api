-- Start library locations
INSERT INTO Library_Location(name)
    VALUES ('Cart');

INSERT INTO Library_Location(name)
    VALUES ('Shelf');

INSERT INTO Library_Location(name)
    VALUES ('Repair');

INSERT INTO Library_Location(name)
    VALUES ('Check-in');

-- End library locations
-- Start phone types
INSERT INTO Phone_Type(id, name)
    VALUES ('H', 'Home');

INSERT INTO Phone_Type(id, name)
    VALUES ('W', 'Work');

INSERT INTO Phone_Type(id, name)
    VALUES ('C', 'Cell');

-- End phone types
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

INSERT INTO Genre(name)
    VALUES ('Fiction');

INSERT INTO Genre(name)
    VALUES ('Gothic Fiction');

-- End genres
-- Start books
-- Start Apothecary
INSERT INTO Publisher(name)
    VALUES ('Square Enix Manga & Books');

INSERT INTO Author(first_name, last_name)
    VALUES ('Natsu', 'Hyuuga');

INSERT INTO Author(first_name, last_name)
    VALUES ('Nekokurage', NULL);

INSERT INTO Book(isbn, title, edition, volume, page_count, publication_date, ddn, publisher_id)
    VALUES ('9781646090709', 'The Apothecary Diaries', '1', '1', 178, '2020-12-08', '741.5952',(
            SELECT
                id
            FROM
                Publisher
            WHERE
                name = 'Square Enix Manga & Books'));

INSERT INTO Book_To_Author(book_isbn, author_id)
    VALUES ('9781646090709',(
            SELECT
                id
            FROM
                Author
            WHERE
                first_name = 'Natsu'
                AND last_name = 'Hyuuga'));

INSERT INTO Book_To_Author(book_isbn, author_id)
    VALUES ('9781646090709',(
            SELECT
                id
            FROM
                Author
            WHERE
                first_name = 'Nekokurage'
                AND last_name IS NULL));

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

-- End Apothecary
-- Start Frieren
INSERT INTO Publisher(name)
    VALUES ('Viz Media');

INSERT INTO Author(first_name, last_name)
    VALUES ('Tsukasa', 'Abe');

INSERT INTO Book(isbn, title, edition, volume, page_count, publication_date, ddn, publisher_id)
    VALUES ('9781974725762', 'Frieren: Beyond Journey''s End', '1', '1', 192, '2021-11-09', '741.5952', 2);

INSERT INTO Book_To_Author(book_isbn, author_id)
    VALUES ('9781974725762',(
            SELECT
                id
            FROM
                Author
            WHERE
                first_name = 'Tsukasa'
                AND last_name = 'Abe'));

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

-- End Frieren
-- J.K. Rowling's Book
INSERT INTO Publisher(name)
    VALUES ('Bloomsbury');

INSERT INTO Author(first_name, last_name)
    VALUES ('J.K.', 'Rowling');

INSERT INTO Book(isbn, title, edition, volume, page_count, publication_date, ddn, publisher_id)
    VALUES ('9780747532743', 'Harry Potter and the Philosopher''s Stone', '1', '1', 223, '1997-06-26', '823.92',(
            SELECT
                id
            FROM
                Publisher
            WHERE
                name = 'Bloomsbury'));

INSERT INTO Book_To_Author(book_isbn, author_id)
    VALUES ('9780747532743',(
            SELECT
                id
            FROM
                Author
            WHERE
                first_name = 'J.K.'
                AND last_name = 'Rowling'));

INSERT INTO Book_To_Genre(book_isbn, genre_id)
    VALUES ('9780747532743',(
            SELECT
                id
            FROM
                Genre
            WHERE
                name = 'Fantasy'));

-- George Orwell's Book
INSERT INTO Publisher(name)
    VALUES ('Secker & Warburg');

INSERT INTO Author(first_name, last_name)
    VALUES ('George', 'Orwell');

INSERT INTO Book(isbn, title, page_count, publication_date, ddn, publisher_id)
    VALUES ('9780451524935', 'Nineteen Eighty-Four', 328, '1949-06-08', '823.912',(
            SELECT
                id
            FROM
                Publisher
            WHERE
                name = 'Secker & Warburg'));

INSERT INTO Book_To_Author(author_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Author
            WHERE
                first_name = 'George'
                AND last_name = 'Orwell'),
            '9780451524935');

INSERT INTO Book_To_Genre(genre_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Genre
            WHERE
                name = 'Drama'), '9780451524935');

INSERT INTO Book_To_Genre(genre_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Genre
            WHERE
                name = 'Thriller'), '9780451524935');

-- Agatha Christie's Book
INSERT INTO Publisher(name)
    VALUES ('Collins Crime Club');

INSERT INTO Author(first_name, last_name)
    VALUES ('Agatha', 'Christie');

INSERT INTO Book(isbn, title, page_count, publication_date, ddn, publisher_id)
    VALUES ('9780007119318', 'Murder on the Orient Express', 256, '1934-01-01', '823.9',(
            SELECT
                id
            FROM
                Publisher
            WHERE
                name = 'Collins Crime Club'));

INSERT INTO Book_To_Author(author_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Author
            WHERE
                first_name = 'Agatha'
                AND last_name = 'Christie'),
            '9780007119318');

INSERT INTO Book_To_Genre(genre_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Genre
            WHERE
                name = 'Mystery'), '9780007119318');

-- Stephen King's Book
INSERT INTO Publisher(name)
    VALUES ('Doubleday');

INSERT INTO Author(first_name, last_name)
    VALUES ('Stephen', 'King');

INSERT INTO Book(isbn, title, edition, page_count, publication_date, ddn, publisher_id)
    VALUES ('9780385121675', 'The Shining', '1', 447, '1977-01-28', '813.6',(
            SELECT
                id
            FROM
                Publisher
            WHERE
                name = 'Doubleday'));

INSERT INTO Book_To_Author(author_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Author
            WHERE
                first_name = 'Stephen'
                AND last_name = 'King'),
            '9780385121675');

INSERT INTO Book_To_Genre(genre_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Genre
            WHERE
                name = 'Thriller'), '9780385121675');

INSERT INTO Book_To_Genre(genre_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Genre
            WHERE
                name = 'Drama'), '9780385121675');

-- Haruki Murakami's Book
INSERT INTO Publisher(name)
    VALUES ('Vintage');

INSERT INTO Author(first_name, last_name)
    VALUES ('Haruki', 'Murakami');

INSERT INTO Book(isbn, title, page_count, publication_date, ddn, publisher_id)
    VALUES ('9780375704024', 'Norwegian Wood', 296, '1987-09-01', '895.6',(
            SELECT
                id
            FROM
                Publisher
            WHERE
                name = 'Vintage'));

INSERT INTO Book_To_Author(author_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Author
            WHERE
                first_name = 'Haruki'
                AND last_name = 'Murakami'),
            '9780375704024');

INSERT INTO Book_To_Genre(genre_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Genre
            WHERE
                name = 'Romance'), '9780375704024');

-- Jane Austen's Book
INSERT INTO Publisher(name)
    VALUES ('T. Egerton, Whitehall');

INSERT INTO Author(first_name, last_name)
    VALUES ('Jane', 'Austen');

INSERT INTO Book(isbn, title, page_count, publication_date, ddn, publisher_id)
    VALUES ('9780141439518', 'Pride and Prejudice', 432, '1813-01-28', '823.7',(
            SELECT
                id
            FROM
                Publisher
            WHERE
                name = 'T. Egerton, Whitehall'));

INSERT INTO Book_To_Author(author_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Author
            WHERE
                first_name = 'Jane'
                AND last_name = 'Austen'),
            '9780141439518');

INSERT INTO Book_To_Genre(genre_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Genre
            WHERE
                name = 'Romance'), '9780141439518');

-- Mark Twain's Book
INSERT INTO Publisher(name)
    VALUES ('Chatto & Windus');

INSERT INTO Publisher(name)
    VALUES ('Charles L. Webster And Company');

INSERT INTO Author(first_name, last_name)
    VALUES ('Mark', 'Twain');

INSERT INTO Book(isbn, title, page_count, publication_date, ddn, publisher_id)
    VALUES ('9780142437179', 'Adventures of Huckleberry Finn', 362, '1885-02-18', '813.54',(
            SELECT
                id
            FROM
                Publisher
            WHERE
                name = 'Chatto & Windus'));

INSERT INTO Book_To_Author(author_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Author
            WHERE
                first_name = 'Mark'
                AND last_name = 'Twain'),
            '9780142437179');

INSERT INTO Book_To_Genre(genre_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Genre
            WHERE
                name = 'Adventure'), '9780142437179');

-- Ernest Hemingway's Book
INSERT INTO Publisher(name)
    VALUES ('Scribner');

INSERT INTO Author(first_name, last_name)
    VALUES ('Ernest', 'Hemingway');

INSERT INTO Book(isbn, title, page_count, publication_date, ddn, publisher_id)
    VALUES ('9780684801223', 'The Old Man and the Sea', 128, '1952-09-01', '813.52',(
            SELECT
                id
            FROM
                Publisher
            WHERE
                name = 'Scribner'));

INSERT INTO Book_To_Author(author_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Author
            WHERE
                first_name = 'Ernest'
                AND last_name = 'Hemingway'),
            '9780684801223');

INSERT INTO Book_To_Genre(genre_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Genre
            WHERE
                name = 'Fiction'), '9780684801223');

-- Leo Tolstoy's Book
INSERT INTO Publisher(name)
    VALUES ('The Russian Messenger');

INSERT INTO Author(first_name, last_name)
    VALUES ('Leo', 'Tolstoy');

INSERT INTO Book(isbn, title, page_count, publication_date, ddn, publisher_id)
    VALUES ('9781400079988', 'War and Peace', 1225, '1869-01-01', '891.73',(
            SELECT
                id
            FROM
                Publisher
            WHERE
                name = 'The Russian Messenger'));

INSERT INTO Book_To_Author(author_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Author
            WHERE
                first_name = 'Leo'
                AND last_name = 'Tolstoy'),
            '9781400079988');

INSERT INTO Book_To_Genre(genre_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Genre
            WHERE
                name = 'Historical Fiction'), '9781400079988');

-- Fyodor Dostoevsky's Book
INSERT INTO Author(first_name, last_name)
    VALUES ('Fyodor', 'Dostoevsky');

INSERT INTO Book(isbn, title, page_count, publication_date, ddn, publisher_id)
    VALUES ('9780140449136', 'Crime and Punishment', 430, '1867-01-01', '891.73',(
            SELECT
                id
            FROM
                Publisher
            WHERE
                name = 'The Russian Messenger'));

INSERT INTO Book_To_Author(author_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Author
            WHERE
                first_name = 'Fyodor'
                AND last_name = 'Dostoevsky'),
            '9780140449136');

INSERT INTO Genre(name)
    VALUES ('Psychological Fiction');

INSERT INTO Book_To_Genre(genre_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Genre
            WHERE
                name = 'Psychological Fiction'), '9780140449136');

-- Mary Shelley's Book
INSERT INTO Publisher(name)
    VALUES ('Lackington, Hughes, Harding, Mavor & Jones');

INSERT INTO Author(first_name, last_name)
    VALUES ('Mary', 'Shelley');

INSERT INTO Book(isbn, title, page_count, publication_date, ddn, publisher_id)
    VALUES ('9781512308051', 'Frankenstein', 280, '1818-01-01', '823.7',(
            SELECT
                id
            FROM
                Publisher
            WHERE
                name = 'Lackington, Hughes, Harding, Mavor & Jones'));

INSERT INTO Book_To_Author(author_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Author
            WHERE
                first_name = 'Mary'
                AND last_name = 'Shelley'),
            '9781512308051');

INSERT INTO Book_To_Genre(genre_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Genre
            WHERE
                name = 'Gothic Fiction'), '9781512308051');

-- J.R.R. Tolkien's Book
INSERT INTO Publisher(name)
    VALUES ('George Allen & Unwin');

INSERT INTO Author(first_name, last_name)
    VALUES ('J.R.R.', 'Tolkien');

INSERT INTO Book(isbn, title, page_count, publication_date, ddn, publisher_id)
    VALUES ('9780547928227', 'The Hobbit', 310, '1937-09-21', '823.91',(
            SELECT
                id
            FROM
                Publisher
            WHERE
                name = 'George Allen & Unwin'));

INSERT INTO Book_To_Author(author_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Author
            WHERE
                first_name = 'J.R.R.'
                AND last_name = 'Tolkien'),
            '9780547928227');

INSERT INTO Book_To_Genre(genre_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Genre
            WHERE
                name = 'Fantasy'), '9780547928227');

-- C.S. Lewis's Book
INSERT INTO Publisher(name)
    VALUES ('Geoffrey Bles');

INSERT INTO Author(first_name, last_name)
    VALUES ('C.S.', 'Lewis');

INSERT INTO Book(isbn, title, edition, page_count, publication_date, ddn, publisher_id)
    VALUES ('9780064471046', 'The Lion, the Witch and the Wardrobe', '1', 208, '1950-10-16', '823.912',(
            SELECT
                id
            FROM
                Publisher
            WHERE
                name = 'Geoffrey Bles'));

INSERT INTO Book_To_Author(author_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Author
            WHERE
                first_name = 'C.S.'
                AND last_name = 'Lewis'),
            '9780064471046');

INSERT INTO Book_To_Genre(genre_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Genre
            WHERE
                name = 'Fantasy'), '9780064471046');

-- Brandon Sanderson's Book
INSERT INTO Publisher(name)
    VALUES ('Tor Books');

INSERT INTO Author(first_name, last_name)
    VALUES ('Brandon', 'Sanderson');

INSERT INTO Book(isbn, title, edition, volume, page_count, publication_date, ddn, publisher_id)
    VALUES ('9780765311788', 'Mistborn: The Final Empire', '1st', '1', 541, '2006-07-17', '813.6',(
            SELECT
                id
            FROM
                Publisher
            WHERE
                name = 'Tor Books'));

INSERT INTO Book_To_Author(author_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Author
            WHERE
                first_name = 'Brandon'
                AND last_name = 'Sanderson'),
            '9780765311788');

INSERT INTO Book_To_Genre(genre_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Genre
            WHERE
                name = 'Fantasy'), '9780765311788');

-- Neil Gaiman's Book
INSERT INTO Publisher(name)
    VALUES ('William Morrow');

INSERT INTO Author(first_name, last_name)
    VALUES ('Neil', 'Gaiman');

INSERT INTO Book(isbn, title, edition, page_count, publication_date, ddn, publisher_id)
    VALUES ('9780060558123', 'American Gods', '1st', 465, '2001-06-19', '813.6',(
            SELECT
                id
            FROM
                Publisher
            WHERE
                name = 'William Morrow'));

INSERT INTO Book_To_Author(author_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Author
            WHERE
                first_name = 'Neil'
                AND last_name = 'Gaiman'),
            '9780060558123');

INSERT INTO Book_To_Genre(genre_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Genre
            WHERE
                name = 'Fantasy'), '9780060558123');

-- Suzanne Collins' Book
INSERT INTO Publisher(name)
    VALUES ('Scholastic Press');

INSERT INTO Author(first_name, last_name)
    VALUES ('Suzanne', 'Collins');

INSERT INTO Book(isbn, title, edition, volume, page_count, publication_date, ddn, publisher_id)
    VALUES ('9780439023528', 'The Hunger Games', '1st', '1', 374, '2008-09-14', '813.6',(
            SELECT
                id
            FROM
                Publisher
            WHERE
                name = 'Scholastic Press'));

INSERT INTO Book_To_Author(author_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Author
            WHERE
                first_name = 'Suzanne'
                AND last_name = 'Collins'),
            '9780439023528');

INSERT INTO Book_To_Genre(genre_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Genre
            WHERE
                name = 'Adventure'), '9780439023528');

INSERT INTO Book_To_Genre(genre_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Genre
            WHERE
                name = 'Thriller'), '9780439023528');

-- Rick Riordan's Book
INSERT INTO Publisher(name)
    VALUES ('Disney-Hyperion');

INSERT INTO Author(first_name, last_name)
    VALUES ('Rick', 'Riordan');

INSERT INTO Book(isbn, title, edition, volume, page_count, publication_date, ddn, publisher_id)
    VALUES ('9780786838653', 'The Lightning Thief', '1st', '1', 377, '2005-06-28', '813.6',(
            SELECT
                id
            FROM
                Publisher
            WHERE
                name = 'Disney-Hyperion'));

INSERT INTO Book_To_Author(author_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Author
            WHERE
                first_name = 'Rick'
                AND last_name = 'Riordan'),
            '9780786838653');

INSERT INTO Book_To_Genre(genre_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Genre
            WHERE
                name = 'Fantasy'), '9780786838653');

INSERT INTO Book_To_Genre(genre_id, book_isbn)
    VALUES ((
            SELECT
                id
            FROM
                Genre
            WHERE
                name = 'Adventure'), '9780786838653');


-- End books
