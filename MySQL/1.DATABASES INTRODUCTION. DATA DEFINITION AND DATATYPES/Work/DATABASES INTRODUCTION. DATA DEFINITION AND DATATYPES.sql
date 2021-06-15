CREATE DATABASE `gamebar`;
Use gamebar;
-- Create Tables
CREATE TABLE `employees`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(50) NOT NULL,
`last_name` VARCHAR(50) NOT NULL
);
 CREATE TABLE`categories`(
 `id` INT PRIMARY KEY AUTO_INCREMENT,
 `name` VARCHAR(50) NOT NULL
 );
 CREATE TABLE`products`(
 `id` INT PRIMARY KEY AUTO_INCREMENT,
 `name` VARCHAR(50) NOT NULL,
 `category_id` INT NOT NULL
 );
 
 
 -- Insert Data in Tables
INSERT INTO `employees` (`first_name`,`last_name`)
VALUES
('Petio','Afkata'),
('Ivan','Stamatov'),
('Petio',"Stamatov");


 -- Alter Tables
ALTER TABLE `employees`
ADD COLUMN `middle_name` VARCHAR(20);


 -- Adding Constraints
ALTER TABLE `products`
ADD CONSTRAINT `fk_products_categories`
FOREIGN KEY (`category_id`)
references `categories`(`id`);


 -- Modifying Columns
ALTER TABLE  `employees`
CHANGE COLUMN `middle_name` `middle_name` VARCHAR(100);
