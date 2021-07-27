CREATE DATABASE `stc`;
USE `stc`;

CREATE TABLE `addresses` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(100) NOT NULL
);

CREATE TABLE `categories` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(10) NOT NULL
);

CREATE TABLE `clients` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`full_name` VARCHAR(50) NOT NULL,
`phone_number` VARCHAR(20) NOT NULL
);

CREATE TABLE `drivers` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(30) NOT NULL,
`last_name` VARCHAR(30) NOT NULL,
`age` INT NOT NULL,
`rating` FLOAT DEFAULT 5.5
);

CREATE TABLE `cars` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`make` VARCHAR(20) NOT NULL,
`model` VARCHAR(20) ,
`year` INT NOT NULL DEFAULT 0,
`mileage` INT  DEFAULT 0,
`condition` CHAR NOT NULL,
`category_id` INT NOT NULL,
CONSTRAINT `fk_cars_categories`
FOREIGN  KEY (`category_id`) 
REFERENCES 	`categories`(`id`)
);

CREATE TABLE `cars_drivers` (
`car_id` INT NOT NULL,
`driver_id` INT NOT NULL,
CONSTRAINT `pk_car_id_driver_id` PRIMARY KEY(`car_id`,`driver_id`),
CONSTRAINT `fk_cars_drivers_cars`
FOREIGN  KEY (`car_id`) 
REFERENCES 	`cars`(`id`),
CONSTRAINT `fk_cars_drivers_drivers`
FOREIGN  KEY (`driver_id`) 
REFERENCES 	`drivers`(`id`)
);

CREATE TABLE `courses` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`from_address_id` INT NOT NULL,
`start` DATETIME NOT NULL,
`bill` DECIMAL (10,2) DEFAULT(10),
`car_id` INT NOT NULL,
`client_id` INT NOT NULL,
CONSTRAINT `fk_courses_addresses`
FOREIGN  KEY (`from_address_id`) 
REFERENCES 	`addresses`(`id`),

CONSTRAINT `fk_courses_cars`
FOREIGN  KEY (`car_id`) 
REFERENCES 	`cars`(`id`),

CONSTRAINT `fk_courses_clients`
FOREIGN  KEY (`client_id`) 
REFERENCES 	`clients`(`id`)
);




-- !!Data Manipulation Language (DML) – 30 pts

-- inset
INSERT INTO clients (`full_name`,`phone_number`)
SELECT concat(`first_name`,' ',`last_name`),concat('(088) 9999',id*2) FROM drivers
WHERE `id`  BETWEEN 10 AND 20; 

-- update
UPDATE `cars`
SET `condition`='C'
WHERE (`mileage`>=800000 OR `mileage` IS NULL) AND `year`<=2010 AND NOT(`make`='Mercedes-Benz');


-- delete 
Delete  FROM `clients` 
WHERE `id` NOT IN (SELECT `client_id`  FROM `courses` )
AND length(full_name)>3;

DELETE cl.* FROM `clients` as `cl` LEFT JOIN `courses` AS cours ON cours.`client_id`=cl.`id`
WHERE cours.`id` IS NULL AND length(full_name)>3;

delete cl.* from clients cl
left join courses c on c.client_id = cl.id
where c.id =NULL;

-- 5.	Cars
SELECT `make`,`model`,`condition` FROM cars AS c;

-- 6. Drivers and Cars
SELECT `first_name`,`last_name`,`make`,`model`,`mileage` FROM `drivers` AS d
JOIN `cars_drivers` AS cd ON cd.`driver_id`=d.`id`
JOIN `cars` AS c ON cd.`car_id`=c.`id`
WHERE `mileage` IS NOT NULL
ORDER BY `mileage` DESC,`first_name`;

-- 7. Number of courses
SELECT c.`id`,c.`make`,c.`mileage`,Count(cours.`id`) AS `count_of_courses`,round(AVG(`bill`),2) AS `avg_bill`  FROM `cars` AS c
LEFT JOIN `courses` AS cours ON cours.`car_id`=c.`id`
GROUP BY c.`id`
HAVING NOT(`count_of_courses`=2)
ORDER BY `count_of_courses` DESC,c.`id`;

-- 8.Regular clients
SELECT `full_name`,COUNT(car.`id`) AS `count_of_cars` ,SUM(`bill`) AS `total_sum` FROM clients AS cl
JOIN `courses` AS cours ON cours.`client_id`=cl.`id`
JOIN `cars` AS car ON cours.`car_id`=car.`id`
GROUP BY cl.`id`
HAVING `full_name` LIKE '_a%' AND `count_of_cars`>1
ORDER BY `full_name`;

 -- 9.	Full information of courses
SELECT adr.`name`,IF( HOUR(`start`)>=6 AND HOUR(`start`)<=20 ,'Day','Night'),`bill`,`full_name`,`make`,`model`,categ.`name` FROM `courses` AS cours
JOIN `clients` AS cl ON cours.`client_id`=cl.`id`
JOIN `cars` AS car ON cours.`car_id`=car.`id`
JOIN `addresses` AS adr ON  cours.`from_address_id`=adr.`id`
JOIN `categories` categ ON car.`category_id`=categ.`id`
ORDER BY cours.`id`;


-- Find all courses by client’s phone number
DELIMITER //
CREATE  FUNCTION `udf_courses_by_client`(phone_num VARCHAR (20)) RETURNS int
    DETERMINISTIC
BEGIN
DECLARE count_c INT;
SET count_c =(SELECT COUNT(cours.`id`) FROM `clients` AS cl
JOIN courses AS cours ON cours.`client_id`=cl.`id`
WHERE phone_num=cl.`phone_number`);

RETURN count_c ;
END//

-- Full info for address
CREATE  PROCEDURE `udp_courses_by_address`(`address_name` VARCHAR (100))
BEGIN

SELECT adr.`name`,`full_name`,IF(`bill`<=20,'Low',IF(`bill`<=30,'Medium','High')),`make`,`condition`,categ.`name` FROM `courses` AS cours
JOIN `clients` AS cl ON cours.`client_id`=cl.`id`
JOIN `cars` AS car ON cours.`car_id`=car.`id`
JOIN `addresses` AS adr ON  cours.`from_address_id`=adr.`id`
JOIN `categories` categ ON car.`category_id`=categ.`id`
WHERE adr.`name`!=NULL
ORDER BY `make`,`full_name`;

END//


SELECT SUBSTING('SoftUni',1,4);













