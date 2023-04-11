package com.application.dao.interfaces;

import com.application.model.*;

import java.util.List;

public interface TaskDao extends Dao<Task> {
    public List<TaskUser> getTasksByUser(User user);
}
