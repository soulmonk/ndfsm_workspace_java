SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `ndfsm`
--
CREATE DATABASE IF NOT EXISTS `ndfsm` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `ndfsm`;

-- --------------------------------------------------------

--
-- Структура таблицы `note_comments`
--

CREATE TABLE IF NOT EXISTS `note_comments` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL,
  `post_id` int(10) unsigned NOT NULL,
  `content` longtext NOT NULL,
  `date_add` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `date_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_note_comment_user_idx` (`user_id`),
  KEY `fk_note_comment_note_post_idx` (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Триггеры `note_comments`
--
DROP TRIGGER IF EXISTS `note_comments_BINS`;
DELIMITER //
CREATE TRIGGER `note_comments_BINS` BEFORE INSERT ON `note_comments`
 FOR EACH ROW BEGIN
  set new.date_add = now();
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Структура таблицы `note_notifications`
--

CREATE TABLE IF NOT EXISTS `note_notifications` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL,
  `content` text NOT NULL,
  `date_add` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `date_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_note_notification_user_idx` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Триггеры `note_notifications`
--
DROP TRIGGER IF EXISTS `note_notification_BINS`;
DELIMITER //
CREATE TRIGGER `note_notification_BINS` BEFORE INSERT ON `note_notifications`
 FOR EACH ROW BEGIN
  set new.date_add = now();
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Структура таблицы `note_posts`
--

CREATE TABLE IF NOT EXISTS `note_posts` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL,
  `title` varchar(255) NOT NULL,
  `excerpt` varchar(255) NOT NULL,
  `content` longtext NOT NULL,
  `date_add` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `date_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_note_post_user_idx` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Триггеры `note_posts`
--
DROP TRIGGER IF EXISTS `note_posts_BINS`;
DELIMITER //
CREATE TRIGGER `note_posts_BINS` BEFORE INSERT ON `note_posts`
 FOR EACH ROW BEGIN
  set new.date_add = now();
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Структура таблицы `persistent_logins`
--

CREATE TABLE IF NOT EXISTS `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `roles`
--

CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL DEFAULT '',
  `authority` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Дамп данных таблицы `roles`
--

INSERT INTO `roles` (`id`, `name`, `authority`) VALUES
(1, 'admin', 'ROLE_ADMIN'),
(2, 'user', 'ROLE_USER'),
(3, 'dev', 'ROLE_DEV');

-- --------------------------------------------------------

--
-- Структура таблицы `time_comments`
--

CREATE TABLE IF NOT EXISTS `time_comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL,
  `value` longtext NOT NULL,
  `task_id` int(11) NOT NULL,
  `from` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `to` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `time_plus` time NOT NULL DEFAULT '00:00:00',
  `status_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `task_id` (`task_id`),
  KEY `status_id` (`status_id`),
  KEY `fk_time_comments_users1_idx` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Структура таблицы `time_comment_statuses`
--

CREATE TABLE IF NOT EXISTS `time_comment_statuses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL,
  `name` varchar(127) NOT NULL,
  `description` longtext NOT NULL,
  `color` varchar(6) NOT NULL DEFAULT '000000',
  PRIMARY KEY (`id`),
  KEY `fk_time_comment_statuses_users1_idx` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Структура таблицы `time_projects`
--

CREATE TABLE IF NOT EXISTS `time_projects` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL,
  `name` varchar(255) NOT NULL DEFAULT '',
  `service_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `service_id` (`service_id`),
  KEY `fk_time_projects_users1_idx` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Структура таблицы `time_services`
--

CREATE TABLE IF NOT EXISTS `time_services` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL,
  `name` varchar(127) NOT NULL DEFAULT '',
  `url` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `fk_time_services_users1_idx` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Дамп данных таблицы `time_services`
--

-- --------------------------------------------------------

--
-- Структура таблицы `time_tasks`
--

CREATE TABLE IF NOT EXISTS `time_tasks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL,
  `name` varchar(255) NOT NULL DEFAULT '',
  `project_id` int(11) NOT NULL,
  `sum` int(11) NOT NULL,
  `ext_id` varchar(32) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `project_id` (`project_id`),
  KEY `fk_time_tasks_users1_idx` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(127) NOT NULL,
  `last_name` varchar(127) NOT NULL,
  `login` varchar(127) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `date_create` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `date_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id`, `first_name`, `last_name`, `login`, `password`, `email`, `enabled`, `date_create`, `date_modified`) VALUES
(1, 'admin', '', 'admin', '$2a$10$NgU.HZaDdXVxTJWPdZKuWenocm1KO483EKkEK8cKYOXzKelPNodEm', 'admin@example.com', 1, '2014-01-31 22:00:00', '2014-02-14 11:04:16');

--
-- Триггеры `users`
--
DROP TRIGGER IF EXISTS `users_BINS`;
DELIMITER //
CREATE TRIGGER `users_BINS` BEFORE INSERT ON `users`
 FOR EACH ROW BEGIN
  set new.date_create = now();
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Структура таблицы `user_role`
--

CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` int(11) unsigned NOT NULL,
  `role_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_users_has_roles_roles1_idx` (`role_id`),
  KEY `fk_users_has_roles_users_idx` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(1, 1),
(1, 2),
(1, 3);

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `note_comments`
--
ALTER TABLE `note_comments`
  ADD CONSTRAINT `fk_note_comment_note_post1` FOREIGN KEY (`post_id`) REFERENCES `note_posts` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_note_comment_users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ограничения внешнего ключа таблицы `note_notifications`
--
ALTER TABLE `note_notifications`
  ADD CONSTRAINT `fk_note_notification_users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ограничения внешнего ключа таблицы `note_posts`
--
ALTER TABLE `note_posts`
  ADD CONSTRAINT `fk_note_post_users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ограничения внешнего ключа таблицы `time_comments`
--
ALTER TABLE `time_comments`
  ADD CONSTRAINT `fk_time_comments_users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `time_comments_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `time_tasks` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `time_comments_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `time_comment_statuses` (`id`);

--
-- Ограничения внешнего ключа таблицы `time_comment_statuses`
--
ALTER TABLE `time_comment_statuses`
  ADD CONSTRAINT `fk_time_comment_statuses_users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ограничения внешнего ключа таблицы `time_projects`
--
ALTER TABLE `time_projects`
  ADD CONSTRAINT `fk_time_projects_users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `time_projects_ibfk_1` FOREIGN KEY (`service_id`) REFERENCES `time_services` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `time_services`
--
ALTER TABLE `time_services`
  ADD CONSTRAINT `fk_time_services_users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ограничения внешнего ключа таблицы `time_tasks`
--
ALTER TABLE `time_tasks`
  ADD CONSTRAINT `fk_time_tasks_users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `time_tasks_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `time_projects` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `fk_users_has_roles_roles1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_users_has_roles_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
SET FOREIGN_KEY_CHECKS=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
