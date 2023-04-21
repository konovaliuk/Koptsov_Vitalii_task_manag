package com.application.dao.interfaces;

import com.application.model.TaskTaskTag;

import java.sql.Connection;

public interface TaskTaskTagDao{
    public void save(Connection connection, TaskTaskTag taskTaskTag);
    public void delete(Connection connection, TaskTaskTag taskTaskTag);
}
