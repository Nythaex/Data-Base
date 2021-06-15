USE `soft_uni`;

-- !!Queries for SoftUni Database!!

-- Find Names of All Employees by First Name
SELECT `first_name`,`last_name` FROM  `employees`
WHERE `first_name` LIKE 'Sa%'
ORDER BY `employee_id`;


-- Find Names of All Employees by Last Name
SELECT `first_name`,`last_name` FROM  `employees`
WHERE `last_name` LIKE '%ei%'
ORDER BY `employee_id`;


-- Find First Names of All Employees
SELECT `first_name` FROM `employees`
WHERE `department_id` IN (3,10) && YEAR(`hire_date`) BETWEEN 1994 AND 2006
ORDER BY `employee_id`;


-- Find All Employees Except Engineers
SELECT `first_name`,`last_name` FROM  `employees`
WHERE NOT(`job_title` LIKE '%engineer%')
ORDER BY `employee_id`;


-- Find Towns with Name Length
SELECT `name` FROM `towns`
WHERE CHAR_LENGTH(`name`)=5 OR CHAR_LENGTH(`name`)=6
ORDER BY `name` ASC;

-- Find Towns Starting With
SELECT `town_id`,`name` FROM `towns`
WHERE LEFT(`name`,1) IN ('R','','B','E')
ORDER BY `name` ASC;


-- Find Towns Not Starting With
SELECT `town_id`,`name` FROM `towns`
WHERE NOT LEFT(`name`,1) IN ('R','D','B')
ORDER BY `name` ASC;


-- Create View Employees Hired After 2000 Year
CREATE VIEW `v_employees_hired_after_2000` AS (
SELECT `first_name`,`last_name` FROM `employees`
WHERE YEAR(`hire_date`)>2000
);
SELECT * FROM `v_employees_hired_after_2000`;


-- Length of Last Name
SELECT `first_name`,`last_name` FROM `employees`
WHERE CHAR_LENGTH(`last_name`)=5 ;



-- !!Queries for Geography Database!!
USE `geography`;

-- Countries Holding 'A' 3 or More Times
SELECT `country_name`,`iso_code` FROM `countries`
WHERE `country_name` LIKE '%a%a%a%'
ORDER BY `iso_code`;


-- Mix of Peak and River Names
SELECT `peak_name`,`river_name`,concat(LOWER(`peak_name`), LOWER(SUBSTRING(`river_name`,2))) FROM `peaks`,`rivers`
WHERE RIGHT(`peak_name`,1)=LEFT(`river_name`,1)
ORDER BY concat(LOWER(`peak_name`), LOWER(SUBSTRING(`river_name`,2))) ASC;
