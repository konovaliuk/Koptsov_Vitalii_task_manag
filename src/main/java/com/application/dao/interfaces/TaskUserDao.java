package com.application.dao.interfaces;

import com.application.model.TaskUser;

import java.sql.Connection;

public interface TaskUserDao{
    public void save(TaskUser taskUser);
    public void updateTaskRole(TaskUser taskUser);
    public void delete(TaskUser taskUser);
}
