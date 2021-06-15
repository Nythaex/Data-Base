USE soft_uni;

-- Managers
SELECT `employee_id`, concat_ws(' ',`first_name`,`last_name`) AS `full_name`,`d`.`department_id`,`d`.`name` AS `department_name` 
FROM `employees` AS `e` JOIN `departments` AS `d` ON `e`.`employee_id`=`d`.`manager_id`
ORDER BY `e`.`employee_id` ASC
LIMIT 5;

-- Towns and Addresses
SELECT t.`town_id`,`name` AS `town_name`,`address_text` FROM `towns` AS `t` JOIN `addresses` AS `a`
ON `t`.`town_id`=`a`.`town_id`
WHERE `t`.`name` IN ('San Francisco', 'Sofia' , 'Carnation')
ORDER BY `t`.`town_id` ASC,`address_id` ASC;

-- Employees Without Managers
SELECT `employee_id`,`first_name`,`last_name`,`department_id`,`salary`
FROM `employees`
WHERE `manager_id` IS NULL;


-- Higher Salary
SELECT COUNT(salary) AS `count` FROM `employees`
WHERE `salary`>(SELECT AVG(`salary`) FROM `employees`);
