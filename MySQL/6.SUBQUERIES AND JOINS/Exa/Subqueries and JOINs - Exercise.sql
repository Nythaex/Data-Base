-- !! soft_uni_database !!
USE `soft_uni`;

-- Employee Address
SELECT `employee_id`,`job_title`,`address_id`,`address_text` FROM `employees` AS `emp`
JOIN `addresses` AS `ad` USING(`address_id`)
ORDER BY `address_id`
limit 5;


-- Addresses with Towns
SELECT `first_name`,`last_name`,`name` AS `town`,`address_text` FROM `employees` AS `emp`
JOIN `addresses` AS `ad` USING(`address_id`)
JOIN `towns` AS `t` ON `t`.`town_id`=`ad`.`town_id`
ORDER BY `first_name`,`last_name`
limit 5;


-- Sales Employee\
SELECT `employee_id`,`first_name`,`last_name`,`name` AS `department_name` FROM `employees` AS `emp`
JOIN `departments` AS `dep` USING(`department_id`)
WHERE dep.`name` = 'Sales'
ORDER BY `employee_id` DESC;

-- Employee Departments
SELECT `employee_id`,`first_name`,`salary`,`name` AS `department_name` FROM `employees` AS `emp`
JOIN `departments` AS `dep` USING(`department_id`)
WHERE `salary`>15000
ORDER BY `department_id` DESC
LIMIT 5;

-- Employees Without Project
SELECT `employee_id`,`first_name` FROM `employees` as `e` LEFT JOIN `employees_projects` USING (`employee_id`)
WHERE `project_id` IS NULL
ORDER BY `employee_id` DESC
LIMIT 3;


-- Employees Hired After
SELECT `first_name`,`last_name`,`hire_date`,`name` AS `dept_name` FROM `employees` AS `emp`
JOIN `departments` AS `dep` USING(`department_id`)
WHERE `hire_date`>'1999/1/1 00:00:00' AND `name` IN('Finance','Sales')
ORDER BY `hire_date` ASC;


-- Employees with Project
SELECT `employee_id`,`first_name`,`name` AS `project_name` FROM `employees` AS `emp` 
JOIN `employees_projects` AS `ep` USING(employee_id)
JOIN `projects` AS `p` ON p.`project_id`=ep.project_id
WHERE DATE(`start_date`)>'2002/8/13' AND `end_date` IS NULL
ORDER BY `first_name`,p.`name`
LIMIT 5;


-- Employee 24
SELECT `employee_id`,`first_name`,IF(YEAR(`start_date`)>=2005,NULL,p.`name`) AS `project_name`
FROM `employees` AS `e` JOIN `employees_projects` AS `ep` USING(`employee_id`)
JOIN `projects` AS `p` ON p.`project_id`=ep.project_id
WHERE `employee_id`=24
ORDER BY `p`.`name`;


-- Employee Manager
SELECT 
    e.`employee_id`,
    e.`first_name`,
    e.`manager_id`,
    `m`.`first_name` AS `manager_name`
FROM
    `employees` AS `e`
        JOIN
    `employees` AS `m` ON e.`manager_id` = m.`employee_id`
WHERE
    e.`manager_id` IN (3 , 7)
ORDER BY `e`.`first_name`;


-- Employee Summary
SELECT 
    e.`employee_id`,
    CONCAT(e.`first_name`, ' ', e.`last_name`) AS `employee_name`,
    CONCAT(m.`first_name`, ' ', m.`last_name`) AS `manager_name`,
    d.`name` AS `department_name`
FROM
    `employees` AS `e`
        JOIN
    `employees` AS `m` ON e.`manager_id` = m.`employee_id`
        JOIN
    `departments` AS `d` ON e.`department_id` = d.`department_id`
ORDER BY `e`.`employee_id`
LIMIT 5;


-- Min Average Salary

     SELECT  MIN(`avg`) AS d
FROM  (SELECT AVG(`salary`) AS `avg`
        FROM `employees`
        GROUP BY `department_id`) 
        AS `ver`;
     
     
SELECT -- no
    MIN((SELECT 
            AVG(m.`salary`)
        FROM
            `employees` AS m
        GROUP BY `department_id`)) AS d
FROM
    `employees`;



-- !! geography !!
USE `geography`;

SELECT `country_code` ,`mountain_range`,`peak_name`,`elevation` FROM `countries` AS c
JOIN `mountains_countries` AS mc  USING(`country_code`)
JOIN `mountains` AS m  ON m.`id`=mc.`mountain_id`
JOIN `peaks` AS p  USING(`mountain_id`)
WHERE `country_code` ='BG' AND `elevation`>2835
ORDER BY `elevation` DESC;


-- Count Mountain Ranges
SELECT `country_code` ,Count(`mountain_range`) as `m_count` FROM `countries` AS cc
JOIN `mountains_countries` AS mc  USING(`country_code`)
JOIN `mountains` AS m  ON m.`id`=mc.`mountain_id`
WHERE `country_code` IN ('BG','RU','US')
GROUP BY `country_code`
ORDER BY `m_count` DESC;


-- Countries with Rivers
SELECT `country_name`,`river_name` FROM `countries` AS c
LEFT JOIN `countries_rivers` AS cr USING(`country_code`)
LEFT JOIN `rivers` AS r ON r.`id`=cr.`river_id`
WHERE `continent_code` = 'AF'
ORDER BY `country_name` ASC
LIMIT 5;


-- Continents and Currencies
SELECT `continent_code`, `currency_code`,
Count(*) AS `currency_usage`
FROM `countries` as c
 GROUP BY `continent_code`,`currency_code`
 HAVING  `currency_usage`>1 AND  `currency_usage`=((SELECT COUNT(*) AS `currency_usage` FROM `countries` as count
WHERE count.`continent_code`=c.`continent_code`
GROUP BY `currency_code`
ORDER BY `currency_usage` DESC
LIMIT 1
))
 ORDER BY `continent_code`,`currency_usage`;



-- Countries Without Any Mountains
SELECT COUNT(*) FROM `countries` AS c
LEFT JOIN `mountains_countries` AS mc  USING(`country_code`)
WHERE mc.`mountain_id` IS NULL;


-- Highest Peak and Longest River by Country
SELECT `country_name`,MAX(`elevation`) AS `highest_peak_elevation`,MAX(`length`) AS `longest_river_length` FROM `countries`
JOIN `countries_rivers` AS cr USING(`country_code`)
JOIN `rivers` AS r ON cr.`river_id`=r.`id`
JOIN `mountains_countries` AS mc USING(`country_code`)
JOIN `mountains` AS m ON mc.`mountain_id`=m.`id`
JOIN `peaks` AS p ON m.`id`=p.`mountain_id`
GROUP BY `country_name`
ORDER BY `highest_peak_elevation` DESC,`longest_river_length` DESC,`country_name` ASC
LIMIT 5;


