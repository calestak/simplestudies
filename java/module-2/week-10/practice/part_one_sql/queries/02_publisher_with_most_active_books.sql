-- 2. Select the name of the publisher with the most books published that are not out of print.
-- Select the number of books published by them (name the column 'books_in_print').
-- (1 row)
SELECT p.publisher_name, Count(b.out_of_print) AS books_in_print
FROM publisher p
LEFT JOIN book b ON b.publisher_id = p.publisher_id
WHERE out_of_print = false
GROUP BY p.publisher_id
ORDER BY books_in_print DESC
LIMIT 1;