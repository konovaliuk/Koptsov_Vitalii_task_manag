package com.application.dao.interfaces;

import com.application.model.TaskTaskTag;

import java.sql.Connection;

public interface TaskTaskTagDao{
    void save(Connection connection, TaskTaskTag taskTaskTag);
    void delete(Connection connection, TaskTaskTag taskTaskTag);
}
