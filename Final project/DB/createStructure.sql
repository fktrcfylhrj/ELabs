-- MySQL Script generated by MySQL Workbench
-- Mon May 10 12:23:39 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`user` ;

CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) UNIQUE NOT NULL,
  `password` VARCHAR(45) NOT NULL, 	
  `role` int NOT NULL CHECK (`role` IN (0, 1, 2)),
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`travel_agent`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`travel_agent` ;

CREATE TABLE IF NOT EXISTS `mydb`.`travel_agent` (
  `agent_id` int NOT NULL,
  `surname_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`agent_id`),
  CONSTRAINT `agent_id_for_agent`
    FOREIGN KEY (`agent_id`)
    REFERENCES `mydb`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`customer` ;

CREATE TABLE IF NOT EXISTS `mydb`.`customer` (
  `customer_id` int NOT NULL,
  `is_regular_customer` TINYINT(1) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `patronymic` VARCHAR(45) NULL,
  PRIMARY KEY (`customer_id`),
  CONSTRAINT `customer_id_for_user`
    FOREIGN KEY (`customer_id`)
    REFERENCES `mydb`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tour`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tour` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tour` (
  `tour_id` int NOT NULL AUTO_INCREMENT,
  `date_start` DATE NOT NULL,
  `date_end` DATE NOT NULL,
  `burning` TINYINT(1) NOT NULL,
  `tour_customer_id` int NOT NULL,
  `tour_agent_id` int NOT NULL,
  `recreation_cost` FLOAT(14,2) UNSIGNED NOT NULL,
  `excursion_cost` FLOAT(14,2) UNSIGNED NOT NULL,
  `shopping_cost` FLOAT(14,2) UNSIGNED NOT NULL,
  `other_expenses` FLOAT(14,2) UNSIGNED NOT NULL,
  PRIMARY KEY (`tour_id`),
  INDEX `customer_id_idx` (`tour_customer_id` ASC) VISIBLE,
  INDEX `travel_agent_id_idx` (`tour_agent_id` ASC) VISIBLE,
  CONSTRAINT `customer_id_for_tour`
    FOREIGN KEY (`tour_customer_id`)
    REFERENCES `mydb`.`customer` (`customer_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `agent_id_for_tour`
    FOREIGN KEY (`tour_agent_id`)
    REFERENCES `mydb`.`travel_agent` (`agent_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
