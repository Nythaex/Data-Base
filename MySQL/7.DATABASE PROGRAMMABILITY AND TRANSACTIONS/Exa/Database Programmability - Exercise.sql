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
CREATE  FUNCTION `ufn_is_word_comprised`(`set_of_letters` varchar(50), `word` varchar(50)) RETURNS int
    DETERMINISTIC
BEGIN
RETURN IF(`word` REGEXP concat('^','[',`set_of_letters`,']','+','$'),1,0);
END//




-- !!Queries for Bank Database!!  
USE `soft_uni`//
-- Find Full Name
CREATE PROCEDURE `usp_get_holders_full_name`()
BEGIN
SELECT concat(`first_name`,' ',`last_name`) AS `full_name` FROM `account_holders`
ORDER BY `full_name`,`id`;
END//


-- People with Balance Higher Than 
CREATE  PROCEDURE `usp_get_holders_with_balance_higher_than`(`value` DECIMAL)
BEGIN
SELECT `first_name`,`last_name` FROM `account_holders` AS ah
JOIN `accounts` AS ac ON ah.`id`=ac.`account_holder_id`
GROUP BY ac.`account_holder_id`
HAVING SUM(`balance`)>`value`;

END//


-- Future Value Function

CREATE FUNCTION `ufn_calculate_future_value` (`initial_sum` DECIMAL(10,4),`yearly_interest_rate`DECIMAL (10,4),`number_of_years` DECIMAL(10,4))
RETURNS DECIMAL(10,4)
DETERMINISTIC
BEGIN
RETURN initial_sum*(POW((1+`yearly_interest_rate`),`number_of_years`));
END//


-- Calculating Interest
CREATE PROCEDURE `usp_calculate_future_value_for_account`(`id` INT,`interest` DECIMAL (10,4))
BEGIN
     SELECT ac.`id`,`first_name`,`last_name`,`balance`,(`ufn_calculate_future_value`(`balance`,`interest`,5)) AS `balance_in_5_years`
     FROM accounts AS ac JOIN `account_holders` AS ah ON ah.`id`=ac.`account_holder_id`
     WHERE `id`=ac.`id`;
END//


-- Deposit Money
CREATE PROCEDURE `usp_deposit_money` (account_id INT, money_amount DECIMAL (10,4)) 
BEGIN
UPDATE `accounts` AS a
SET `balance`=`balance`+`money_amount`
WHERE `money_amount`>=0 AND `account_id`=a.`id`;

END//


-- Withdraw Money
CREATE  PROCEDURE `usp_withdraw_money`(account_id INT, money_amount DECIMAL (20,4))
BEGIN
UPDATE `accounts` AS a
SET `balance`=`balance`-`money_amount`
WHERE `money_amount`>=0 AND `account_id`=a.`id` AND `balance`>=`money_amount`;

END//

-- Money Transfer
CREATE  PROCEDURE `usp_transfer_money`(from_account_id INT,to_account_id INT, amount DECIMAL(10,4))
BEGIN
	IF(SELECT a.`id` FROM `accounts` AS a WHERE a.`id`=from_account_id) AND (SELECT a.`id` FROM `accounts` AS a WHERE a.`id`=to_account_id)
    AND (amount>=0) AND ((SELECT `balance` FROM `accounts` AS a WHERE a.`id`=from_account_id)>=`amount`)
    THEN CALL usp_withdraw_money(`from_account_id`,amount);
    CALL usp_deposit_money(`to_account_id`,amount);
    ELSE ROLLBACK;
    END IF;

END//

-- Log Accounts Trigger
CREATE table `logs`(
log_id INT PRIMARY KEY AUTO_INCREMENT, 
account_id INT, 
old_sum  DECIMAL(20,4), 
new_sum DECIMAL(20,4))//

CREATE  TRIGGER `accounts_AFTER_UPDATE` AFTER UPDATE ON `accounts` FOR EACH ROW
BEGIN
       INSERT INTO  `logs` (account_id,`old_sum`,new_sum)
       VALUES(old.`id`,old.balance,new.`balance`);
END//



-- Emails Trigger


CREATE TABLE notification_emails (
    id INT PRIMARY KEY AUTO_INCREMENT,
    recipient INT,
    subject VARCHAR(200),
    body VARCHAR(200)
)//
CREATE TRIGGER `soft_uni`.`logs_AFTER_INSERT` AFTER UPDATE ON `logs` FOR EACH ROW
BEGIN
       INSERT INTO notification_emails (`recipient`,`subject`,`body`)
       VALUES(new.account_id,concat('Balance change for account: ',new.account_id),concat('On ',NOW(),' your balance was changed from ',new.`old_sum`,' to ',new.`new_sum`));
END//






