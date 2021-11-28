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
-- Table `graphium`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `graphium`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `fname` VARCHAR(45) NOT NULL,
  `lname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;
-- -----------------------------------------------------
-- Table `graphium`.`organisation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `graphium`.`organisation` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `institution` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `institution_UNIQUE` (`institution` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;
-- -----------------------------------------------------
-- Table `graphium`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `graphium`.`access` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `institution` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_organisation_user_idx` (`user_id` ASC) ,
  INDEX `fk_organisation_idx` (`organisation_id` ASC) ,
  CONSTRAINT `fk_organisation_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `graphium`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_organisation`
    FOREIGN KEY (`organisation_id`)
    REFERENCES `graphium`.`organisation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)

ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;
-- -----------------------------------------------------
-- Table `graphium`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `graphium`.`admin` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;

DELIMITER ;
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

