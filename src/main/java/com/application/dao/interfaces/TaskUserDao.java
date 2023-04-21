package com.application.dao.interfaces;

import com.application.model.TaskUser;

import java.sql.Connection;

public interface TaskUserDao{
    public void save(Connection connection, TaskUser taskUser);
    public void updateTaskRole(Connection connection, TaskUser taskUser);
    public void delete(Connection connection, TaskUser taskUser);
}
