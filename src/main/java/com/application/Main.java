package com.application;

import com.application.dao.ConnectionPool;
import com.application.dao.DaoFactory;
import com.application.dao.impl.JDBC.JDBCDaoFactory;
import com.application.model.*;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            DaoFactory factory = new JDBCDaoFactory();
            List<UserRole> userRoles = factory.getUserRoleDao().getAll(connection);
            System.out.println(userRoles.get(1).getName());
            User newUser = new User(-1,"a", "s", "b","c","b",
                    "@sadsd","asdasdas","rr-00", "dsdg@afas",
                    "0000000000", LocalDate.now(), LocalDate.now(), userRoles.get(1));
            newUser = factory.getUserDao().save(connection, newUser);
            newUser = factory.getUserDao().save(connection, newUser);
            Task task = factory.getTaskDao().get(connection, 1);
            TaskRole taskRole = factory.getTaskRoleDao().getAll(connection).get(0);
            TaskUser taskUser = new TaskUser(task, newUser, taskRole);
            factory.getTaskUserDao().save(connection, taskUser);
            List<TaskUser> tasksUserOnNewUser = factory.getTaskDao().getTasksByUser(connection, newUser);
            Task[] tasksOnNewUser = tasksUserOnNewUser.stream().map((x) -> x.getTask()).toArray(Task[]::new);
            System.out.println(tasksOnNewUser);
            factory.getTaskUserDao().delete(connection, taskUser);
            factory.getUserDao().delete(connection, newUser.getId());
        }
        finally {
            ConnectionPool.getInstance().shutdown();
        }
    }
}
