INSERT INTO
    Publisher(name)
VALUES
    ('Square Enix Manga & Books');

INSERT INTO
    Publisher(name)
VALUES
    ('Viz Media');

INSERT INTO
    Author(first_name, last_name)
VALUES
    ('Natsu', 'Hyuuga');

INSERT INTO
    Author(first_name, last_name)
VALUES
    ('Nekokurage', NULL);

INSERT INTO
    Author(first_name, last_name)
VALUES
    ('Tsukasa', 'Abe');

INSERT INTO
    Book(
        isbn,
        title,
        volume,
        page_count,
        publication_date,
        ddn,
        publisher_id
    )
VALUES
    (
        '9781646090709',
        'The Apothecary Diaries',
        '1',
        178,
        '2020-12-08',
        '741.5952',
        1
    );

INSERT INTO
    Book(
        isbn,
        title,
        volume,
        page_count,
        publication_date,
        ddn,
        publisher_id
    )
VALUES
    (
        '9781974725762',
        'Frieren: Beyond Journey''s End',
        '1',
        192,
        '2021-11-09',
        '741.5952',
        2
    );

INSERT INTO
    Book_To_Author(book_isbn, author_id)
VALUES
    ('9781646090709', 1);

INSERT INTO
    Book_To_Author(book_isbn, author_id)
VALUES
    ('9781646090709', 2);

INSERT INTO
    Book_To_Author(book_isbn, author_id)
VALUES
    ('9781974725762', 3);