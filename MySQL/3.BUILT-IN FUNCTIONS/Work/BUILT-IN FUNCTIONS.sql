
USE `book_library`;
-- Find Book Titles
	SELECT `title` FROM `books`
    WHERE SUBSTR(`title`,1,3)='The'
    ORDER BY `id` ASC;
    
    
-- Replace Titles
SELECT REPLACE(`title`,'The','***') AS `title` FROM `books`
WHERE SUBSTR(`title`,1,3)='The'
ORDER BY `id` ASC;


-- Sum Cost of All Books
SELECT ROUND(SUM(`cost`),2) AS PRICE FROM `books`;



-- Days Lived
SELECT concat(`first_name`,' ',`last_name`) AS `Full Name`,datediff(`died`,`born`) AS `Days Lived` FROM `authors`; 


-- Harry Potter Books
SELECT 	`title` AS `title` FROM books
WHERE `title` LIKE '%Harry Poter%';
