package com.application;

import com.application.dao.impl.DaoFactory;
import com.application.dao.impl.JDBC.JDBCDaoFactory;
import com.application.dao.interfaces.*;
import com.application.model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            DaoFactory factory = new JDBCDaoFactory(connection);
            List<UserRole> userRoles = factory.getUserRoleDao().getAll();
            System.out.println(userRoles.get(1).getName());
            User newUser = new User(-1,"b","c","b",
                    "@sadsd","asdasdas","rr-00", "dsdg@afas",
                    "0000000000", LocalDate.now(), LocalDate.now(), userRoles.get(1));
            newUser = factory.getUserDao().save(newUser);
            Task task = factory.getTaskDao().get(1);
            TaskRole taskRole = factory.getTaskRoleDao().getAll().get(0);
            TaskUser taskUser = new TaskUser(task, newUser, taskRole);
            factory.getTaskUserDao().save(taskUser);
            List<TaskUser> tasksUserOnNewUser = factory.getTaskDao().getTasksByUser(newUser);
            Task[] tasksOnNewUser = tasksUserOnNewUser.stream().map((x) -> x.getTask()).toArray(Task[]::new);
            System.out.println(tasksOnNewUser);
            factory.getTaskUserDao().delete(taskUser);
            factory.getUserDao().delete(newUser.getId());
        }
        finally {
            ConnectionPool.getInstance().shutdown();
        }
    }
}
