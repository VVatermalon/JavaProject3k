use student_base;
-- ---
-- Globals
-- ---

-- SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
-- SET FOREIGN_KEY_CHECKS=0;

-- ---
-- Table 'users'
--
-- ---

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` INTEGER AUTO_INCREMENT NOT NULL,
  `login` VARCHAR(60) NULL DEFAULT NULL,
  `password` VARCHAR(30) NULL DEFAULT NULL,
  `role` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ---
-- Table 'teacher_information'
--
-- ---

DROP TABLE IF EXISTS `teacher_information`;

CREATE TABLE `teacher_information` (
  `id` INTEGER AUTO_INCREMENT NOT NULL,
  `userID` INTEGER NULL DEFAULT NULL,
  `firstName` VARCHAR(60) NULL DEFAULT NULL,
  `lastName` VARCHAR(60) NULL DEFAULT NULL,
  `middleName` VARCHAR(50) NULL DEFAULT NULL,
  `department` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ---
-- Table 'student_information'
--
-- ---

DROP TABLE IF EXISTS `student_information`;

CREATE TABLE `student_information` (
  `id` INTEGER AUTO_INCREMENT NOT NULL,
  `userID` INTEGER NOT NULL,
  `firstName` VARCHAR(50) NULL DEFAULT NULL,
  `lastName` VARCHAR(50) NULL DEFAULT NULL,
  `middleName` VARCHAR(50) NULL DEFAULT NULL,
  `faculty` VARCHAR(60) NULL DEFAULT NULL,
  `birthday` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ---
-- Table 'progress'
--
-- ---

DROP TABLE IF EXISTS `progress`;

CREATE TABLE `progress` (
  `id` INTEGER AUTO_INCREMENT NOT NULL,
  `studentID` INTEGER NOT NULL,
  `teacherID` INTEGER NOT NULL,
  `subject` VARCHAR(30) NULL DEFAULT NULL,
  `note` INTEGER NULL DEFAULT NULL,
  `note_day` DATE NULL DEFAULT NULL,
  `comment` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ---
-- Table 'admin_information'
--
-- ---

DROP TABLE IF EXISTS `admin_information`;

CREATE TABLE `admin_information` (
  `id` INTEGER AUTO_INCREMENT NOT NULL,
  `userID` INTEGER NULL NOT NULL,
  `firstName` VARCHAR(50) NULL DEFAULT NULL,
  `lastName` VARCHAR(50) NULL DEFAULT NULL,
  `middleName` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ---
-- Table 'department_subject'
--
-- ---

DROP TABLE IF EXISTS `department_subject`;

CREATE TABLE `department_subject` (
  `name` VARCHAR(20) AUTO_INCREMENT NOT NULL,
  `subject` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`name`)
);

-- ---
-- Table 'subject'
--
-- ---

DROP TABLE IF EXISTS `subject`;

CREATE TABLE `subject` (
  `name` VARCHAR(30) AUTO_INCREMENT NOT NULL,
  PRIMARY KEY (`name`)
);

-- ---
-- Table 'department'
--
-- ---

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `department` VARCHAR(20) AUTO_INCREMENT NOT NULL,
  PRIMARY KEY (`department`)
);

-- ---
-- Foreign Keys
-- ---

ALTER TABLE `teacher_information` ADD FOREIGN KEY (userID) REFERENCES `users` (`id`);
ALTER TABLE `teacher_information` ADD FOREIGN KEY (department) REFERENCES `department` (`department`);
ALTER TABLE `student_information` ADD FOREIGN KEY (userID) REFERENCES `users` (`id`);
ALTER TABLE `progress` ADD FOREIGN KEY (studentID) REFERENCES `users` (`id`);
ALTER TABLE `progress` ADD FOREIGN KEY (teacherID) REFERENCES `users` (`id`);
ALTER TABLE `admin_information` ADD FOREIGN KEY (userID) REFERENCES `users` (`id`);
ALTER TABLE `department_subject` ADD FOREIGN KEY (name) REFERENCES `department` (`department`);
ALTER TABLE `department_subject` ADD FOREIGN KEY (subject) REFERENCES `subject` (`name`);

-- ---
-- Table Properties
-- ---

-- ALTER TABLE `users` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `teacher_information` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `student_information` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `progress` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `admin_information` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `department_subject` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `subject` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `department` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ---
-- Test Data
-- ---

-- INSERT INTO `users` (`id`,`login`,`password`,`role`) VALUES
-- ('','','','');
-- INSERT INTO `teacher_information` (`id`,`userID`,`firstName`,`lastName`,`middleName`,`department`) VALUES
-- ('','','','','','');
-- INSERT INTO `student_information` (`id`,`userID`,`firstName`,`lastName`,`middleName`,`faculty`,`birthday`) VALUES
-- ('','','','','','','');
-- INSERT INTO `progress` (`id`,`studentID`,`teacherID`,`subject`,`note`,`note_day`,`comment`) VALUES
-- ('','','','','','','');
-- INSERT INTO `admin_information` (`id`,`userID`,`firstName`,`lastName`,`middleName`) VALUES
-- ('','','','','');
-- INSERT INTO `department_subject` (`name`,`subject`) VALUES
-- ('','');
-- INSERT INTO `subject` (`name`) VALUES
-- ('');
-- INSERT INTO `department` (`department`) VALUES
-- ('');
-- переделать studentrep subjectsrep вывод списка на страницу, контролер студент рейтинг