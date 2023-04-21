package com.application.dao.impl.JDBC;

import com.application.dao.interfaces.TaskTaskTagDao;
import com.application.model.TaskTaskTag;

import java.sql.*;

public class JDBCTaskTaskTag implements TaskTaskTagDao {
    private final String INSERT_TAG_TASK = "insert into `task_tag_task`(`task_id`,`task_tag_id`) values (?, ?)";
    private final String DELETE_TAG_TASK = "delete from `task_tag_task` where task_id = ? and task_tag_id = ?";

    @Override
    public void save(Connection connection, TaskTaskTag taskTaskTag) {
        try (PreparedStatement save = connection.prepareStatement(INSERT_TAG_TASK))
        {
            save.setLong(1,taskTaskTag.getTaskId());
            save.setLong(2,taskTaskTag.getTaskTagId());
            save.executeUpdate();
            ResultSet id = save.getGeneratedKeys();
        }
        catch (SQLException e)
        {
            //TODO: add logging
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Connection connection, TaskTaskTag taskTaskTag) {
        try (PreparedStatement delete = connection.prepareStatement(DELETE_TAG_TASK))
        {
            delete.setLong(1,taskTaskTag.getTaskId());
            delete.setLong(2,taskTaskTag.getTaskTagId());
            delete.executeUpdate();
        }
        catch (SQLException e)
        {
            //TODO: add logging
            throw new RuntimeException(e);
        }
    }
}
