-- Queries for SoftUni Database !!
USE SOFTUNI;


-- 	Employees with Salary Above 35000
DELIMITER //
CREATE  PROCEDURE `usp_get_employees_salary_above_35000`()
BEGIN
(SELECT `first_name`,`last_name` FROM employees
WHERE `salary`>35000
ORDER BY `first_name`,`last_name`,`employee_id`);
END
//

CALL usp_get_employees_salary_above_35000//



-- Employees with Salary Above Number
CREATE  PROCEDURE `usp_get_employees_salary_above`(`given_salary` DECIMAL(10,4))
BEGIN
(SELECT `first_name`,`last_name` FROM employees
WHERE `salary`>=`given_salary`
ORDER BY `first_name`,`last_name`,`employee_id`);
END
//
CALL `usp_get_employees_salary_above`(45000)//



-- Town Names Starting With
CREATE  PROCEDURE `usp_get_towns_starting_with`(`given_alpha` VARCHAR(10))
BEGIN
(SELECT `name` FROM towns
WHERE UPPER(LEFT(`name`,LENGTH(`given_alpha`)))=UPPER(`given_alpha`)
ORDER BY `name`);
END
//
CALL `usp_get_towns_starting_with`('s');//


-- 	Employees from Town
DELIMITER //
CREATE  PROCEDURE `usp_get_employees_from_town`(`town_name` VARCHAR(10))
BEGIN
(SELECT `first_name`,`last_name` FROM employees AS e
JOIN `addresses` AS a USING(`address_id`) 
JOIN `towns` AS t USING(`town_id`)
WHERE t.`name`=`town_name`
ORDER BY `first_name`,`last_name`,`employee_id`);
END
//
CALL `usp_get_employees_from_town`('Sofia')//


-- Salary Level Function
CREATE FUNCTION `ufn_get_salary_level`(`given_salary` DECIMAL(10,2)) RETURNS varchar(20) CHARSET utf8
    DETERMINISTIC
BEGIN
DECLARE result VARCHAR(20);

IF(`given_salary`<30000)
 THEN SET result='Low';
 ELSEIF(`given_salary`<=50000)
  THEN SET result='Average';
 ELSE
    SET result='High';

END IF;
RETURN result;
END//


-- Employees by Salary Level
CREATE  PROCEDURE `usp_get_employees_by_salary_level`(`level` VARCHAR(20))
BEGIN
SELECT `first_name`,`last_name` FROM employees AS e
WHERE `level`=`ufn_get_salary_level`(e.`salary`)
ORDER BY `first_name` DESC,`last_name` DESC;
END//

CALL usp_get_employees_by_salary_level('High')//


-- 	Define Function



-- !!Queries for Bank Database!!  
-- Find Full Name


-- People with Balance Higher Than 



-- Future Value Function

CREATE FUNCTION `ufn_calculate_future_value` (`initial_sum` DECIMAL(10,4),`yearly_interest_rate`DECIMAL (10,4),`number_of_years` DECIMAL(10,4))
RETURNS DECIMAL(10,4)
DETERMINISTIC
BEGIN
RETURN initial_sum*(POW((1+`yearly_interest_rate`),`number_of_years`));
END//


-- Calculating Interest


-- Deposit Money


-- Withdraw Money


-- Money Transfer


-- Log Accounts Trigger


-- Emails Trigger