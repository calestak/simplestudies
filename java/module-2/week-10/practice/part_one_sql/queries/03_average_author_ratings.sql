-- 3. List the first and last name of all authors, separated by a space, (name the column 'author') and the average star rating of their books (name the column 'average_rating').
-- Round average_rating to 2 decimal places.
-- Order by the average rating, lowest first.
-- (16 rows)
SELECT pe.first_name || ' ' || last_name AS author, CAST(AVG(star_rating)AS DECIMAL(10,2)) AS average_rating
FROM person pe
JOIN book_author ba ON ba.author_id = pe.person_id 
JOIN book b ON b.book_id = ba.book_id
GROUP BY author
ORDER BY average_rating ASC;