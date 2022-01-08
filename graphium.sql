-- MySQL Workbench Forward Engineering
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema graphium
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `graphium` ;
-- -----------------------------------------------------
-- Schema graphium
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `graphium` DEFAULT CHARACTER SET utf8 ;
USE `graphium` ;
-- -----------------------------------------------------
-- Table `graphium`.`users`
-- -----------------------------------------------------
-- DROP table IF EXISTS `graphium`.`users` ;
CREATE TABLE IF NOT EXISTS `graphium`.`users` (
  `user_id` INT(10) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(20) NOT NULL,
  -- `first_name` VARCHAR(50) NOT NULL,
--   `last_name` VARCHAR(50) NOT NULL,
  `pass` VARCHAR(80) NULL DEFAULT NULL,
  `enabled` TINYINT(4) NULL DEFAULT 1,
  `role` VARCHAR(50) NOT NULL,
  `organisation_id` BIGINT(10),
  PRIMARY KEY	(`user_id`),
  INDEX `organisation_idx` (`organisation_id` ASC) ,
  CONSTRAINT `fk_organisation_user1`
    FOREIGN KEY (`organisation_id`)
    REFERENCES `graphium`.`organisations` (`organisation_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
-- -----------------------------------------------------
-- Table `graphium`.`organisations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `graphium`.`organisations`(
`organisation_id` BIGINT(10) NOT NULL AUTO_INCREMENT,
`organisation_name` VARCHAR(100) NOT NULL,
PRIMARY KEY (`organisation_id`));
-- -----------------------------------------------------
-- Table `graphium`.`files`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `graphium`.`files`(
`file_id` BIGINT(10) NOT NULL AUTO_INCREMENT,
`file_name` VARCHAR(50) NOT NULL,
`file_object` LONGBLOB NOT NULL,
PRIMARY KEY (`file_id`));

-- -----------------------------------------------------
-- Table `graphium`.`partner_organisations`
-- -----------------------------------------------------
DROP table IF EXISTS `graphium`.`partner_organisations` ;
CREATE TABLE IF NOT EXISTS `graphium`.`partner_organisations` (
  `partner_organisation_id` INT(10) NOT NULL AUTO_INCREMENT,
  `partner_organisation_name` VARCHAR(20) NOT NULL,
  `organisation_id` BIGINT(10),
  PRIMARY KEY	(`partner_organisation_id`),
  INDEX `organisation_idx` (`organisation_id` ASC) ,
  CONSTRAINT `fk_organisation_partner`
    FOREIGN KEY (`organisation_id`)
    REFERENCES `graphium`.`organisations` (`organisation_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

DELIMITER ;
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;