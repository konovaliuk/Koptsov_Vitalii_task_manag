package com.application.dao.interfaces;

import com.application.model.TaskUser;

import java.sql.Connection;

public interface TaskUserDao{
    void save(Connection connection, TaskUser taskUser);
    void updateTaskRole(Connection connection, TaskUser taskUser);
    void delete(Connection connection, TaskUser taskUser);
}
