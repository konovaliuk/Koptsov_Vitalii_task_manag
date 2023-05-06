package com.application.dao.interfaces;

import com.application.model.TaskUser;
import com.application.model.User;
import com.application.model.UserRole;
import com.application.model.Task;

import java.sql.Connection;
import java.util.List;

public interface UserDao extends Dao<User> {
    User getUserByLoginOrEmail(Connection connection, String login);
    List<TaskUser> getUsersByTask(Connection connection, Task task);
    List<User> getUsersByUserRole(Connection connection, UserRole userRole);
}
