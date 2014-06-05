SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_ALL_TABLES,ALLOW_INVALID_DATES';

ALTER TABLE `time_comments`
DROP FOREIGN KEY `time_comments_ibfk_1`,
DROP FOREIGN KEY `time_comments_ibfk_2`;

ALTER TABLE `time_projects`
DROP FOREIGN KEY `time_projects_ibfk_1`;

ALTER TABLE `time_tasks`
DROP FOREIGN KEY `time_tasks_ibfk_1`;

ALTER TABLE `time_comment_statuses`
CHANGE COLUMN `id` `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT ;

ALTER TABLE `time_comments`
CHANGE COLUMN `id` `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT ,
CHANGE COLUMN `task_id` `task_id` INT(11) UNSIGNED NOT NULL ,
CHANGE COLUMN `status_id` `status_id` INT(11) UNSIGNED NOT NULL ;

ALTER TABLE `time_projects`
CHANGE COLUMN `id` `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT ,
CHANGE COLUMN `service_id` `service_id` INT(11) UNSIGNED NOT NULL ;

ALTER TABLE `time_services`
CHANGE COLUMN `id` `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT ;

ALTER TABLE `time_tasks`
CHANGE COLUMN `id` `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT ,
CHANGE COLUMN `project_id` `project_id` INT(11) UNSIGNED NOT NULL ;

CREATE TABLE IF NOT EXISTS `time_records` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) UNSIGNED NOT NULL,
  `task` VARCHAR(255) NOT NULL DEFAULT '',
  `date_from` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00',
  `date_to` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00',
  `break_time` TIME NOT NULL DEFAULT '00:00:00',
  `dirty_time` TIME NOT NULL DEFAULT '00:00:00',
  `comment` LONGTEXT NULL DEFAULT NULL,
  `status_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_time_projects_users1_idx` (`user_id` ASC),
  INDEX `fk_time_records_time_comment_statuses1_idx` (`status_id` ASC),
  CONSTRAINT `fk_time_projects_users10`
  FOREIGN KEY (`user_id`)
  REFERENCES `users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_time_records_time_comment_statuses1`
  FOREIGN KEY (`status_id`)
  REFERENCES `time_comment_statuses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci;

ALTER TABLE `time_comments`
ADD CONSTRAINT `time_comments_ibfk_1`
FOREIGN KEY (`task_id`)
REFERENCES `time_tasks` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
ADD CONSTRAINT `time_comments_ibfk_2`
FOREIGN KEY (`status_id`)
REFERENCES `time_comment_statuses` (`id`);

ALTER TABLE `time_projects`
ADD CONSTRAINT `time_projects_ibfk_1`
FOREIGN KEY (`service_id`)
REFERENCES `time_services` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE `time_tasks`
ADD CONSTRAINT `time_tasks_ibfk_1`
FOREIGN KEY (`project_id`)
REFERENCES `time_projects` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
