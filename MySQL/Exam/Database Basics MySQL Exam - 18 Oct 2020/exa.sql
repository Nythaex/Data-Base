CREATE DATABASE `softuni_stores_system`;
USE `softuni_stores_system`;

CREATE TABLE `pictures`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`url` VARCHAR(100) NOT NULL,
`added_on` DATETIME NOT NULL
);

CREATE TABLE `categories`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(40) NOT NULL
);

CREATE TABLE `products`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(40) NOT NULL,
`best_before` DATE,
`price` DECIMAL(10,2) NOT NULL,
`description` TEXT,
`category_id` INT NOT NULL,
`picture_id`INT NOT NULL,
CONSTRAINT `fk_products_category`
FOREIGN KEY (`category_id`)
REFERENCES `categories`(`id`),

CONSTRAINT `fk_products_pictures`
FOREIGN KEY (`picture_id`)
REFERENCES `pictures`(`id`)
 );
 
 CREATE TABLE `towns`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(20) NOT NULL UNIQUE
);
 
  CREATE TABLE `addresses`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL UNIQUE,
`town_id` INT NOT NULL,
CONSTRAINT `fk_addresses_towns`
FOREIGN KEY (`town_id`)
REFERENCES `towns`(`id`)
);


  CREATE TABLE `stores`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(20) NOT NULL UNIQUE,
`rating` FLOAT NOT NULL,
`has_parking` BOOL DEFAULT FALSE,
`address_id` INT NOT NULL,
CONSTRAINT `fk_stores_addresses`
FOREIGN KEY (`address_id`)
REFERENCES `addresses`(`id`)
);

 CREATE TABLE `products_stores`(
`product_id` INT,
`store_id` INT,
CONSTRAINT `pk_products_stores` PRIMARY KEY (`product_id`,`store_id`),
CONSTRAINT `fk_products_stores_products`
FOREIGN KEY (`product_id`)
REFERENCES `products`(`id`),
CONSTRAINT `fk_products_stores_stores`
FOREIGN KEY (`store_id`)
REFERENCES `stores`(`id`)
);

  CREATE TABLE `employees`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(15) NOT NULL,
`middle_name` CHAR,
`last_name` VARCHAR(20) NOT NULL,
`salary` DECIMAL(19,2) DEFAULT 0,
`hire_date` DATE NOT NULL,
`manager_id` INT,
`store_id` INT NOT NULL,
CONSTRAINT `fk_employees_stores`
FOREIGN KEY (`store_id`)
REFERENCES `stores`(`id`),
CONSTRAINT `fk_employees_employees`
FOREIGN KEY (`manager_id`)
REFERENCES `employees`(`id`)
);



-- !!Section 2: Data Manipulation Language (DML) – 30 pts!!

-- Insert
INSERT INTO `products_stores` (`product_id`,`store_id`)
SELECT `id`,1 FROM `products` AS p 
LEFT JOIN `products_stores` AS ps ON p.`id`=ps.`product_id`
WHERE `store_id` IS NULL ;

-- Update
ALTER TABLE `employees` 
DROP CONSTRAINT `fk_employees_managers` ;

UPDATE `employees` AS e JOIN `stores` AS s ON `e`.`store_id`=s.`id`
SET `manager_id`=3 AND `salary`=`salary`-500
WHERE YEAR(`hire_date`)>2003 AND s.`name` NOT IN ('Cardguard','Veribet');


--  Delete
DELETE FROM `employees`
WHERE `manager_id` IS NOT NULL AND `salary`>=6000;


-- !! Querying – 50 pts!!

-- Employees
SELECT `first_name`,`middle_name`,`last_name`,`salary`,`hire_date` FROM `employees`
ORDER BY `hire_date` DESC;

-- Products with old pictures
SELECT `name` AS `product_name`,`price`,`best_before`,concat(LEFT(`description`,10),'...') AS `short_description`,pic.`url` FROM products  AS p
JOIN `pictures` AS pic ON pic.`id`=p.`picture_id`
WHERE LENGTH(`description`)>100 AND YEAR(`added_on`)<2019 AND `price`>20
ORDER BY `price` DESC;

-- Counts of products in stores
SELECT s.`name`,COUNT(p.`id`) AS `product_count`,ROUND(AVG(p.`price`),2) AS `avg` FROM products AS p 
RIGHT JOIN `products_stores` AS ps ON ps.`product_id`=p.`id`
RIGHT JOIN `stores` AS s ON ps.`store_id`=s.`id`
GROUP BY s.`name`
ORDER BY `product_count` DESC,`avg` DESC ,s.`id`;


-- Specific employee
SELECT concat_ws(' ',`first_name`,`last_name`) AS `Full_name`,s.`name` AS `Full_name`,a.`name` AS `address`,`salary` FROM `employees` AS e
JOIN `stores` AS s ON e.`store_id`=s.`id`
JOIN `addresses` AS a ON a.`id`=s.`address_id`
WHERE `salary`<4000 AND a.`name` LIKE '%5%' AND LENGTH(s.`name`)>8 AND `last_name` LIKE '%n';

-- Find all information of stores
SELECT REVERSE(s.`name`) AS `reversed_name`,concat(UPPER(t.`name`),'-',a.`name`) AS `full_address`,COUNT(e.`id`) AS `employees_count` FROM  `employees` AS e
JOIN `stores` AS s ON e.`store_id`=s.`id`
JOIN `addresses` AS a ON a.`id`=s.`address_id`
JOIN `towns` AS t ON t.`id`=a.`town_id`
GROUP BY s.`name`
HAVING `employees_count`>=1
ORDER BY `full_address`;


-- Find full name of top paid employee by store name
DELIMITER //
CREATE FUNCTION `udf_top_paid_employee_by_store`(`store_name` VARCHAR(50)) RETURNS varchar(50) CHARSET utf8mb4
    DETERMINISTIC
BEGIN   
   
RETURN (SELECT concat(`first_name`,' ',`middle_name`,'.',' ',`last_name`,' ','works in store for ',FLOOR(DATEDIFF('2020-10-18',`hire_date`)/360),' years') FROM employees AS e
JOIN stores AS s ON e.`store_id`=s.`id`
WHERE s.`name`=`store_name`
ORDER BY `salary` DESC
LIMIT 1);
END//

-- Update product price by address
CREATE  PROCEDURE `udp_update_product_price`(`address_name` VARCHAR (50))
BEGIN
DECLARE  bonus DECIMAL;
IF(LEFT(`address_name`,1)='0')
THEN SET bonus=100;
Else SET bonus=200;
End IF;

	UPDATE `products` AS p JOIN products_stores ps ON   p.`id`=ps.`product_id`
    JOIN stores AS s ON s.`id`=ps.`store_id`
    JOIN addresses AS a ON a.`id`=s.`address_id`
    SET `price`=`price`+`bonus`
    WHERE a.`name`=`address_name`;
END//



