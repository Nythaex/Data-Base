-- !!GRINGOTTS DATABASE QUARY!!
USE `gringotts`;

-- Records' Count
SELECT COUNT(*) FROM `wizzard_deposits`;


-- Longest Magic Wand
SELECT MAX(`magic_wand_size`) FROM `wizzard_deposits`;


-- Longest Magic Wand per Deposit Groups
SELECT  `deposit_group`,MAX(`magic_wand_size`) AS MAX FROM `wizzard_deposits`
GROUP BY `deposit_group`
ORDER BY `MAX`,`deposit_group` ASC;


-- Smallest Deposit Group Per Magic Wand Size*
SELECT  `deposit_group` FROM `wizzard_deposits`
GROUP BY `deposit_group`
ORDER BY AVG(magic_wand_size) ASC,`deposit_group` ASC
LIMIT 1;


-- Deposits Sum
SELECT `deposit_group`,SUM(`deposit_amount`) AS 'total_sum' FROM `wizzard_deposits`
GROUP BY `deposit_group`
ORDER BY `total_sum` ASC;


-- Deposits Sum for Ollivander Family
SELECT `deposit_group`,SUM(`deposit_amount`) AS 'total_sum' FROM `wizzard_deposits`
WHERE `magic_wand_creator`='Ollivander family'
GROUP BY `deposit_group`
ORDER BY `deposit_group` ASC;


-- Deposits Filter
SELECT `deposit_group`,SUM(`deposit_amount`) AS 'total_sum' FROM `wizzard_deposits`
WHERE `magic_wand_creator`='Ollivander family'
GROUP BY `deposit_group`
HAVING `total_sum`<150000
ORDER BY `total_sum` DESC;


-- Deposit Charge
SELECT `deposit_group`,`magic_wand_creator`,MIN(ROUND(`deposit_charge`,2)) FROM `wizzard_deposits`
GROUP BY `deposit_group`,`magic_wand_creator`
ORDER BY `magic_wand_creator`,`deposit_group`;


-- Age Groups
SELECT CASE
       WHEN `age` BETWEEN 0 AND 10 THEN '[0-10]'
       WHEN `age` BETWEEN 11 AND 20 THEN '[11-20]'
       WHEN `age` BETWEEN 21 AND 30 THEN '[21-30]'
       WHEN `age` BETWEEN 31 AND 40 THEN '[31-40]'
       WHEN `age` BETWEEN 41 AND 50 THEN '[41-50]'
       WHEN `age` BETWEEN 51 AND 60 THEN '[51-60]'
       ELSE '[61+]' 
       END
       AS 'age_group',COUNT(*) AS `wizard_count` FROM `wizzard_deposits`
       GROUP BY `age_group`
       ORDER BY `wizard_count`;
       
   

-- First Letter
SELECT SUBSTRING(`first_name`,1,1) AS `first_letter` FROM `wizzard_deposits`
WHERE `deposit_group`='Troll Chest'
GROUP BY `first_letter`
ORDER BY `first_letter`;


-- Average Interest
SELECT `deposit_group`,`is_deposit_expired`,AVG(`deposit_interest`) AS `average_interest` FROM `wizzard_deposits`
WHERE `deposit_start_date` > '1985-01-01'
GROUP BY `deposit_group`,`is_deposit_expired`
ORDER BY `deposit_group` DESC,`is_deposit_expired` ASC;


-- !!SOFT_UNI DATABASE QUARY!!
USE `soft_uni`;

-- Employees Minimum Salaries
SELECT `department_id`,MIN(`salary`) AS	`minimum_salary` FROM `employees`
WHERE `hire_date`> '2000-01-01'
GROUP BY `department_id`
HAVING `department_id` IN (2,5,7)
ORDER BY `department_id` ASC;


--  Employees Average Salaries
CREATE TABLE `new_one` AS 
SELECT * FROM `employees`
WHERE `salary`>30000;
DELETE FROM `new_one`
WHERE `manager_id`=42;
UPDATE `new_one`
SET `salary`=`salary`+5000
WHERE `department_id`=1;

SELECT `department_id`,AVG(`salary`) AS `avg_salary` FROM `new_one`
GROUP BY `department_id`
ORDER BY `department_id `;



-- Employees Maximum Salaries
SELECT `department_id`,MAX(`salary`) AS `max_salary` FROM `employees`
GROUP BY `department_id` 
HAVING `max_salary` NOT BETWEEN 30000 AND 70000
ORDER BY  `department_id` ASC;
 

-- Employees Count Salaries
SELECT COUNT(*) FROM `employees`
WHERE `manager_id` IS NULL;


-- 3rd Highest Salary
SELECT `S1`.`department_id`,(
SELECT DISTINCT `salary` FROM `employees` AS `S2`
WHERE `S1`.`department_id`=`S2`.`department_id`
ORDER BY `salary` DESC
LIMIT 1 OFFSET 2
) AS `third_highest_salary` FROM `employees` AS `S1`
GROUP BY `S1`.`department_id`
HAVING `third_highest_salary` IS NOT NULL
ORDER BY `S1`.`department_id` ASC;


-- Salary Challenge
SELECT `first_name`,`last_name`,`department_id` FROM `employees` AS `S1`
WHERE `salary`>(
SELECT AVG(`salary`) FROM `employees`
WHERE `department_id`=`S1`.`department_id`
)
ORDER BY `department_id`,`employee_id`
LIMIT 10;


-- Departments Total Salaries
SELECT `department_id`,SUM(`salary`) FROM `employees`
GROUP BY `department_id`
ORDER BY `department_id` ASC;