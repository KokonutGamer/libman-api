INSERT INTO Publisher(id, name)
    VALUES (1, 'Square Enix');

INSERT INTO Publisher(id, name)
    VALUES (2, 'Viz Media');

INSERT INTO Book(isbn, title, volume, page_count, publication_date, ddn, publisher_id)
    VALUES ('9781646090709', 'The Apothecary Diaries', '1', 178, '2020-12-08', '741.5952',(
            SELECT
                id
            FROM
                Publisher
            WHERE
                name LIKE 'Square Enix'));

INSERT INTO Book(isbn, title, volume, page_count, publication_date, ddn, publisher_id)
    VALUES ('9781974725762', 'Frieren: Beyond Journey''s End', '1', 192, '2021-11-09', '741.5952',(
            SELECT
                id
            FROM
                Publisher
            WHERE
                name LIKE 'Viz Media'));

