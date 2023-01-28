-- 4. List the first and last name of all persons, separated by a space, (name the column 'full_name') who have written at least 2 forewords.
-- Include the count of forewords written by each person (name the column 'foreword_count').
-- Order by full_name ascending.
-- (7 rows)
SELECT pe.first_name || ' ' || pe.last_name AS full_name, COUNT(b.foreword_by)AS foreword_count
FROM person pe
JOIN book_author ba ON ba.author_id = pe.person_id 
JOIN book b ON b.book_id = ba.book_id
GROUP BY full_name 
ORDER BY full_name ASC;