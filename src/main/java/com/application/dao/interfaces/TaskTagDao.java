package com.application.dao.interfaces;

import com.application.model.Task;
import com.application.model.TaskTag;

import java.sql.Connection;
import java.util.List;

public interface TaskTagDao extends Dao<TaskTag> {
    List<TaskTag> getTaskTagsByTask(Connection connection, Task task);
}
