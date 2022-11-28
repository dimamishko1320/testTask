drop database if exists testTask;
CREATE SCHEMA IF NOT EXISTS testTask DEFAULT CHARACTER SET utf8 ;
USE testTask ;

CREATE TABLE IF NOT EXISTS Accounts (
id INT NOT NULL,
amount long NOT NULL,
PRIMARY KEY (id))
ENGINE = InnoDB;


select * from Accounts;
SELECT id, amount from Accounts
