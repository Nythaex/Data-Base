CREATE DATABASE fsd;
USE fsd;

CREATE TABLE `players`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(10) NOT NULL,
`last_name` VARCHAR(20) NOT NULL,
`age` INT NOT NULL DEFAULT 0,
`position` CHAR(1) NOT NULL ,
`salary` DECIMAL(10,2) NOT NULL DEFAULT 0,
`hire_date` DATETIME ,
`skills_data_id` INT NOT NULL,
`team_id` INT 
-- FK
);
CREATE TABLE `coaches`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(10) NOT NULL,
`last_name` VARCHAR(20) NOT NULL,
`salary` DECIMAL(10,2) NOT NULL DEFAULT 0,
`coach_level` INT NOT NULL DEFAULT 0
);

CREATE TABLE `players_coaches`(
`player_id` INT ,
`coach_id` INT ,
CONSTRAINT `pk_players_coaches` PRIMARY KEY (`player_id`,`coach_id`),
CONSTRAINT `fk_players_coaches_coaches` 
FOREIGN KEY (`coach_id`)
REFERENCES `coaches`(`id`),
FOREIGN KEY (`player_id`)
REFERENCES `players`(`id`)
);

CREATE TABLE `countries`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL
);

CREATE TABLE `towns`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL,
`country_id` INT NOT NULL,
CONSTRAINT `fk_towns_countries` 
FOREIGN KEY (`country_id`)
REFERENCES `countries`(`id`)
);

CREATE TABLE `stadiums`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL,
`capacity` INT NOT NULL,
`town_id` INT NOT NULL,
CONSTRAINT `fk_stadiums_towns` 
FOREIGN KEY (`town_id`)
REFERENCES `towns`(`id`)
);

CREATE TABLE `teams`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL,
`established` DATE NOT NULL,
`fan_base` BIGINT NOT NULL DEFAULT 0,
`stadium_id` INT NOT NULL,
CONSTRAINT `fk_teams_stadiums` 
FOREIGN KEY (`stadium_id`)
REFERENCES `stadiums`(`id`)
);

CREATE TABLE `skills_data`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`dribbling` INT NOT NULL,
`pace` INT NOT NULL,
`passing` INT NOT NULL,
`shooting` INT NOT NULL,
`speed` INT NOT NULL,
`strength` INT NOT NULL
);

ALTER TABLE `players`
ADD CONSTRAINT `pk_players_skills_data`
FOREIGN KEY (`skills_data_id`)
REFERENCES `skills_data`(`id`),
ADD CONSTRAINT `pk_players_teams`
FOREIGN KEY (`team_id`)
REFERENCES `teams`(`id`);


-- 2 Data Manipulation Language (DML) 
-- Insert 
 INSERT INTO `coaches` (`first_name`,`last_name`,`salary`,`coach_level`)
 SELECT `first_name`,`last_name`,`salary`*2,length(`first_name`) FROM `players`
 WHERE `age`>=45;
 
-- Update 
UPDATE `coaches` AS c
SET `coach_level`=`coach_level`+1
WHERE (SELECT COUNT(`player_id`) FROM `players_coaches` as pc
WHERE c.`id`=pc.`coach_id`
)>0 AND LEFT(c.`first_name`,1)='A';

-- Delete
DELETE FROM `players` AS p
WHERE `age`>45;

SELECT COUNT(*) FROM players;
	
-- 3: Querying 
-- Players 
SELECT `first_name`,`age`,`salary` FROM `players`
ORDER BY `salary` DESC;

-- Young offense players without contract
SELECT p.`id`,concat(`first_name`,' ',`last_name`) AS `full_name`,`age`,`position`,`hire_date` FROM `players` AS p
JOIN `skills_data` AS sd ON p.`skills_data_id`=sd.`id`
WHERE `age`<23 AND `position`='A' AND `hire_date` IS NULL AND `strength`>50
ORDER BY `salary`,`age`;

-- Detail info for all teams 
SELECT `name` AS `team_name`,`established`,`fan_base`,COUNT(p.`id`) AS `players_count` FROM `teams` AS t
LEFT JOIN `players` AS p ON t.`id`=p.`team_id`
GROUP BY t.`id`
ORDER BY `players_count` DESC,`fan_base` DESC;


-- The fastest player by towns
SELECT  MAX(`speed`) AS`max_speed` ,t.`name` AS `town_name` FROM `players` AS p
RIGHT JOIN `skills_data` AS sd ON p.`skills_data_id`=sd.`id`
RIGHT JOIN `teams` AS tm ON p.`team_id`=tm.`id`
RIGHT JOIN `stadiums` AS s ON tm.`stadium_id`=s.`id`
RIGHT JOIN `towns` AS t ON s.`town_id`=t.`id`
WHERE NOT(tm.`name`='Devify')
GROUP BY t.`name`
ORDER BY `max_speed` DESC,t.`name` ASC;


-- Total salaries and players by country
SELECT  c.`name`,COUNT(p.`id`) AS `total_count_of_players`,SUM(`salary`) AS `total_sum_of_salaries` FROM `players` AS p
RIGHT JOIN `teams` AS tm ON p.`team_id`=tm.`id`
RIGHT JOIN `stadiums` AS s ON tm.`stadium_id`=s.`id`
RIGHT JOIN `towns` AS t ON s.`town_id`=t.`id`
RIGHT JOIN `countries` AS c ON c.`id`=t.`country_id`
GROUP BY c.`name`
ORDER BY `total_count_of_players` DESC,c.`name` ASC;


-- Section 4: Programmability 
-- Find all players that play on stadium
DELIMITER //
CREATE FUNCTION `udf_stadium_players_count` (`stadium_name` VARCHAR(30))
RETURNS INTEGER DETERMINISTIC
BEGIN
 DECLARE `result` INT;
RETURN (SELECT COUNT(p.`id`) FROM players AS p
 JOIN `teams` AS tm ON p.`team_id`=tm.`id`
 JOIN `stadiums` AS s ON tm.`stadium_id`=s.`id`
 WHERE s.`name`=`stadium_name`);
END//

SELECT `udf_stadium_players_count`('Jaxworks')//


-- Find good playmaker by teams
CREATE  PROCEDURE `udp_find_playmaker`(`min_dribble_points` INT,`team_name` VARCHAR (45))
BEGIN
    SELECT concat(`first_name`,' ',`last_name`) AS `full_name`,`age`,`salary`,`dribbling`,`speed`,tm.`name` AS `team_name` FROM players AS p
      JOIN `skills_data` AS sd ON p.`skills_data_id`=sd.`id`
      JOIN `teams` AS tm ON p.`team_id`=tm.`id`
      WHERE sd.`dribbling`>`min_dribble_points` AND  tm.`name`=`team_name` AND sd.`speed`>(SELECT AVG(`speed`) FROM `skills_data`)
      ORDER BY sd.`speed` DESC
      LIMIT 1;
END//
