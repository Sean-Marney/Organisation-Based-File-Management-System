CREATE DATABASE IF NOT EXISTS graphium;
USE `graphium` ;

CREATE TABLE `files` (
  `file_id` int(11) NOT NULL,
  `access_type` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_object` longblob DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`file_id`)
) ;

CREATE TABLE `organisations` (
  `organisation_id` int(11) NOT NULL AUTO_INCREMENT,
  `organisation_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`organisation_id`)
) ;

CREATE TABLE `partner_organisations` (
  `partner_organisation_id` int(11) NOT NULL AUTO_INCREMENT,
  `partner_organisation_name` varchar(255) DEFAULT NULL,
  `organisation_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`partner_organisation_id`)
) ;

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `enabled` bit(1) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `organisation_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ;