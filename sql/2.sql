INSERT INTO `user_role`(`name`) values ("admin");
INSERT INTO `user_role`(`name`) values ("user");

INSERT INTO `task_status`(`name`) values ("закінчений");
INSERT INTO `task_status`(`name`) values ("почато");
INSERT INTO `task_status`(`name`,`description`) values ("очікуємо відповідь","очікаємо відповідь від людей, які не належать організації");
INSERT INTO `task_status`(`name`) values ("призупинено");

INSERT INTO `task_role`(`name`) values ("голова таску");
INSERT INTO `task_role`(`name`) values ("учасник таску");
INSERT INTO `task_role`(`name`) values ("наглядач");

INSERT INTO `task_tag`(`name`) values ("основна діяльність");
INSERT INTO `task_tag`(`name`) values ("сайд проект");