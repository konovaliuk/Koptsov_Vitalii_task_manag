INSERT INTO `user`(`login`, `password`, `first_name`,`last_name`,`middle_name`, `telegram_tag`, `faculty`, `group`, `email`, `phone_number`, `day_of_birth`, `day_of_admission`, `user_role_id`) VALUES
				("jincooz", "asdasfdasfewfewf", "Віталій", "Копцов", "Олегович", "@jincooz", "ІПСА", "КІ-01", "shaddyreaper@gmail.com", "0950368358", "2003-03-06", "2022-03-06", "1");
INSERT INTO `user`(`login`, `password`, `first_name`,`last_name`,`middle_name`, `telegram_tag`, `faculty`, `group`, `email`, `phone_number`, `day_of_birth`, `day_of_admission`, `user_role_id`) VALUES
				("founa", "asdasfdsdfd", "Аонасія", "Ролова", "Фанова", "@founa", "ФСП", "СЛ-90", "woius@lll.kpi.ua", "0950524568", "2002-10-25", "2021-03-06", "2");
INSERT INTO `user`(`login`, `password`, `first_name`,`last_name`,`middle_name`, `telegram_tag`, `faculty`, `group`, `email`, `phone_number`, `day_of_birth`, `day_of_admission`, `user_role_id`) VALUES
				("0nolik0", "pdfsdsdf", "Філій", "Джон", "", "@nolik", "ІПСА", "КА-01", "nolik@example.com", "0950352390", "2003-05-11", "2020-03-06", "2");
                
INSERT INTO `task`(`name`, `description`, `creation_date`, `deadline_date`, `task_status_id`, `priority`) VALUES
				("Полагодити перший відділ світч", "Немає зв'зку з сервером. Проблема в провідці, напевно.", "2023-04-06 18:00", NOW(), 3, 255);
INSERT INTO `task_tag_task`(`task_id`, `task_tag_id`) VALUES
				(1, 2);


INSERT INTO `task`(`name`, `description`, `creation_date`, `deadline_date`, `task_status_id`, `priority`) VALUES
				("Розробити новий дизайн лого", "Старий застарів.)", NOW(), "2023-05-06 18:00", 1, 0);
INSERT INTO `task_tag_task`(`task_id`, `task_tag_id`) VALUES
				(2, 2);
INSERT INTO `task_tag_task`(`task_id`, `task_tag_id`) VALUES
				(2, 1);
                
INSERT INTO `task_user`(`task_id`,`user_id`,`task_role_id`) VALUES
			(1,1,1);
            
INSERT INTO `task_user`(`task_id`,`user_id`,`task_role_id`) VALUES
			(1,2,3);
            
INSERT INTO `task_user`(`task_id`,`user_id`,`task_role_id`) VALUES
			(1,3,2);

INSERT INTO `task_user`(`task_id`,`user_id`,`task_role_id`) VALUES
			(2,2,1);