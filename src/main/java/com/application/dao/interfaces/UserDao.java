package com.application.dao.interfaces;

import com.application.model.TaskUser;
import com.application.model.User;
import com.application.model.UserRole;
import com.application.model.Task;

import java.util.List;

public interface UserDao extends Dao<User> {
    public User getLoginInfo(String login);
    public void updateLoginInfo(User user);
    public List<TaskUser> getUsersByTask(Task task);
    public List<User> getUsersByUserRole(UserRole userRole);
}
