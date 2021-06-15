USE `soft_usp_get_employees_by_salary_leveluni`;

-- Count Employees by Town
DELIMITER ::
CREATE DEFINER=`root`@`localhost` FUNCTION `ufn_count_employees_by_town`(`town` VARCHAR(20)) RETURNS int
    DETERMINISTIC
BEGIN
RETURN (
SELECT COUNT(*) FROM employees AS e
JOIN `addresses` AS a USING(`address_id`) 
JOIN `towns` AS t USING(`town_id`) 
GROUP BY t.`name`
HAVING t.`name`=`town`);

END 
::
DELIMITER ;


-- Employees Promotion
DELIMITER &&
CREATE PROCEDURE `usp_raise_salaries`(`department_name` VARCHAR(20))
BEGIN
UPDATE `employees` JOIN `departments` AS d USING(`department_id`)
SET `salary`=salary*1.05                          
WHERE d.`name`=`department_name`;
END &&
DELIMITER ;
CALL usp_raise_salaries('Engineering');

-- no
UPDATE `employees` 
SET `salary`=salary*1.05                          
WHERE `employee_id` IN ((SELECT `employee_id` FROM `employees`  AS e
JOIN `departments` AS d USING(`department_id`)
WHERE 'Engineering'=d.`name`
));
-- no


-- Employees Promotion by ID
DELIMITER //
CREATE  PROCEDURE `usp_raise_salary_by_id`(`id` INT)
BEGIN
START TRANSACTION;

IF ((SELECT `employee_id` FROM `employees`
    WHERE `employee_id`=`id`
    LIMIT 1
)=`id`)
THEN UPDATE `employees`
SET `salary`=`salary`*1.05
WHERE `employee_id`=`id`;
ELSE 
ROLLBACK;
END IF;
COMMIT;
END//
DELIMITER ;
CALL `usp_raise_salary_by_id`(1);
SELECT * FROM employees;


-- Triggered
CREATE TABLE deleted_employees(
	employee_id INT PRIMARY KEY,
	first_name VARCHAR(20),
	last_name VARCHAR(20),
	middle_name VARCHAR(20),
	job_title VARCHAR(50),
	department_id INT,
	salary DOUBLE 
);
DELIMITER $$

CREATE  TRIGGER `employees_test_BEFORE_DELETE` BEFORE DELETE ON `employees` FOR EACH ROW BEGIN
     INSERT INTO `deleted_employees` (`employee_id`,`first_name`,`last_name`,`middle_name`,`job_title`,`department_id`,`salary`)
     VALUES(old.`employee_id`,old.`first_name`,old.`last_name`,old.`middle_name`,old.`job_title`,old.`department_id`,old.`salary`);
END $$

DELETE FROM new_employees Where employee_id = 1;
SELECT * FROM deleted_employees;