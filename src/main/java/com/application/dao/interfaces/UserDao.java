package com.application.dao.interfaces;

import com.application.model.TaskUser;
import com.application.model.User;
import com.application.model.UserRole;
import com.application.model.Task;

import java.sql.Connection;
import java.util.List;

public interface UserDao extends Dao<User> {
    public User getLoginInfo(Connection connection, String login);
    public void updateLoginInfo(Connection connection, User user);
    public List<TaskUser> getUsersByTask(Connection connection, Task task);
    public List<User> getUsersByUserRole(Connection connection, UserRole userRole);
}
