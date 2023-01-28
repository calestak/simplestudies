-- 2. List the first and last name of all authors (name the column 'author') and the number of books they've written (name the column 'num_books').
-- Order the results by the number of books in descending order (highest first), then by alphabetical order of the author's first name.
-- Tip: make sure to add a space between the author's first and last names.
-- (16 rows)
SELECT pe.first_name || ' ' || last_name AS author, COUNT(b.book_id) AS num_books
FROM person pe
JOIN book_author ba ON ba.author_id = pe.person_id 
JOIN book b ON b.book_id = ba.book_id
GROUP BY author
ORDER BY num_books DESC, author ASC;
