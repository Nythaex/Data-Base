USE `camp`;

-- Mountains and Peaks
CREATE TABLE `Mountains`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR (45)
);

CREATE TABLE `Peaks`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR (45),
`mountain_id` INT
);

ALTER TABLE `Peaks`
ADD CONSTRAINT `fk_peaks_montains`
FOREIGN KEY (`mountain_id`)
REFERENCES `Mountains`(`id`);


-- Trip Organization
SELECT `driver_id`,`vehicle_type`,concat(`first_name`,' ',`last_name`)
 FROM `vehicles` JOIN `campers`
 ON`vehicles`.`driver_id`=`campers`.`id`;
 
 
 -- SoftUni Hiking
 SELECT `starting_point` AS `route_starting_point`,`end_point`,`leader_id` AS `route_ending_point`,concat(`first_name`,' ',`last_name`) FROM `routes` AS r
 JOIN `campers` AS `c` ON r.`leader_id`=c.`id`;
 
 -- Delete Mountains
CREATE TABLE `mountains`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR (45)
);

 CREATE TABLE `peaks`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR (45),
`mountain_id` INT
);

ALTER TABLE `peaks`
ADD CONSTRAINT `fk_peaks_montains`
FOREIGN KEY (`mountain_id`)
REFERENCES `mountains`(`id`) ON DELETE CASCADE;
 
 INSERT INTO `mountains` (`name`)
 VALUES('deleted'),('nonDeleted');
 
 INSERT INTO `peaks` (`name`,`mountain_id`)
 VALUES('kiro',1),('kiro2',1),('petio',2);
 
 DELETE FROM `mountains`
 WHERE `id`=1;
 
-- Project Management DB*
CREATE TABLE `clients`(
`id` INT(11) AUTO_INCREMENT PRIMARY KEY,
`client_name` VARCHAR(100)
);

CREATE TABLE `projects`(
`id` INT(11) AUTO_INCREMENT PRIMARY KEY,
`client_id` INT(111),
 CONSTRAINT `fk_projects_clients`
FOREIGN KEY (`client_id`)
REFERENCES clients(`id`)
);

CREATE TABLE `employees`(
`id` INT(11) AUTO_INCREMENT PRIMARY KEY,
`first_name` VARCHAR(30),
`last_name` VARCHAR(30),
`project_id` INT(11),
 CONSTRAINT `fk_employees_projects`
FOREIGN KEY (`project_id`)
REFERENCES `projects`(`id`)
);
 
 ALTER TABLE `projects`
 ADD COLUMN `project_lead_id` INT(11),
 ADD CONSTRAINT `fk_projects_employees`
 FOREIGN KEY (`project_lead_id`)
 REFERENCES `employees`(`id`);
 