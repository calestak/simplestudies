-- 4. Add a "Sports" genre to the genre table. Add the movie "Coach Carter" to the newly created genre. (1 row each)

INSERT INTO genre(genre_name)
VALUES ('Sports');
UPDATE movie_genre
SET (genre_id) 
WHERE genre_id = (SELECT movie_id FROM movie WHERE title = 'Coach Carter');