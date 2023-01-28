-- 5. List all book titles and the first and last name of the person who wrote the foreword (name the column 'foreword_author') for books that Moishe Reiling was an author.
-- Order by book title (A-Z).
-- Tip: make sure to add a space between the author's first and last names.
-- (5 rows)
SELECT b.book_title, pe.first_name || ' ' || pe.last_name AS foreword_author
FROM person pe
JOIN book_author ba ON ba.author_id = pe.person_id 
JOIN book b ON b.book_id = ba.book_id
WHERE first_name LIKE 'Moishe%'
ORDER BY book_title ASC;
