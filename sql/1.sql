drop database if exists tasks_control;
create database tasks_control
DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
use tasks_control;

create table `right` (
	`id` bigint unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
	primary key (`id`),
	unique key `right_unq` (`name`)
) engine = InnoDB;

create table `user_role` (
	`id` bigint unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
	primary key (`id`),
	unique key `role_unq` (`name`)
) engine = InnoDB;

create table `user_role_right`(
	`right_id` bigint unsigned NOT NULL,
    `user_role_id` bigint unsigned NOT NULL,
    primary key (`right_id`, `user_role_id`)
) engine= InnoDB;

create table `user` (
	`id` bigint unsigned NOT NULL AUTO_INCREMENT,
    `login` varchar(255) NULL,
    `password` char(32) NULL,
    `first_name` varchar(255) NOT NULL,
    `last_name` varchar(255) NOT NULL,
    `middle_name` varchar(255),
    `telegram_tag` varchar(255) NOT NULL,
    `faculty` varchar(255) NOT NULL,
    `group` varchar(255) NOT NULL,
    `email` varchar(255) NOT NULL,
    `phone_number` char(13) NOT NULL,
    `day_of_birth` date NOT NULL,
    `day_of_admission` date NOT NULL,
    `user_role_id` bigint unsigned NOT NULL,
    primary key (`id`),
    unique key `user_unq1` (`first_name`,`last_name`,`middle_name`),
    unique key `user_unq2` (`email`),
    unique key `user_unq3` (`phone_number`),
    unique key `user_unq4` (`login`)
) engine = InnoDB;

create table `task` (
	`id` bigint unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
    `description` varchar(255) NOT NULL,
    `creation_date` datetime NOT NULL,
    `deadline_date` datetime NOT NULL,
    `task_status_id` bigint unsigned NOT NULL,
    `priority` tinyint unsigned NOT NULL,
    primary key (`id`)
) engine = InnoDB;

create table `task_role` (
	`id` bigint unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
    primary key (`id`),
    unique key (`name`)
) engine = InnoDB;

create table `task_role_right`(
	`right_id` bigint unsigned NOT NULL,
    `task_role_id` bigint unsigned NOT NULL,
    primary key (`right_id`, `task_role_id`)
) engine= InnoDB;

create table `task_user`(
	`task_id` bigint unsigned NOT NULL,
    `user_id` bigint unsigned NOT NULL,
    `task_role_id` bigint unsigned NOT NULL,
    primary key (`task_id`,`user_id`)
) engine = InnoDB;

create table `task_status` (
	`id` bigint unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
    `description` varchar(255),
    primary key (`id`),
    unique key (`name`)
) engine = InnoDB;

create table `task_tag` (
	`id` bigint unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
    `description` varchar(255),
    primary key (`id`),
    unique key (`name`)
) engine = InnoDB;

create table `task_tag_task` (
	`task_id` bigint unsigned NOT NULL,
    `task_tag_id` bigint unsigned NOT NULL,
    primary key(`task_id`,`task_tag_id`)
) engine = InnoDB;

alter table `user` add constraint `fk_user_role` foreign key (`user_role_id`) REFERENCES `user_role` (`id`);
alter table `user_role_right` add constraint `fk_user_role_right_user_role` foreign key(`user_role_id`) references `user_role` (`id`);
alter table `user_role_right` add constraint `fk_user_role_right_right` foreign key(`right_id`) references `right` (`id`);
alter table `task_role_right` add constraint `fk_task_role_right_task_role` foreign key(`task_role_id`) references `task_role` (`id`);
alter table `task_role_right` add constraint `fk_task_role_right_right` foreign key(`right_id`) references `right` (`id`);
alter table `task` add constraint `fk_task_task_status` foreign key (`task_status_id`) REFERENCES `task_status` (`id`);
alter table `task_user` add constraint `fk_task_user_user` foreign key (`user_id`) REFERENCES `user` (`id`);
alter table `task_user` add constraint `fk_task_user_task` foreign key (`task_id`) REFERENCES `task` (`id`);
alter table `task_user` add constraint `fk_task_user_task_role` foreign key (`task_role_id`) REFERENCES `task_role` (`id`);
alter table `task_tag_task` add constraint `fk_task_tag_task_task_tag` foreign key (`task_tag_id`) REFERENCES `task_tag` (`id`);
alter table `task_tag_task` add constraint `fk_task_tag_task_task` foreign key (`task_id`) REFERENCES `task` (`id`);