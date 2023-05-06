package com.application.dao.impl.JDBC;

import com.application.dao.interfaces.TaskTaskTagDao;
import com.application.model.TaskTaskTag;

import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JDBCTaskTaskTagDao implements TaskTaskTagDao {
    private static final Logger LOGGER = LogManager.getLogger(JDBCTaskTaskTagDao.class);
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
            LOGGER.error("Can`t save TaskTaskTag. TaskTaskTag = " + taskTaskTag.toString() + ". " + e.getMessage());
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
            LOGGER.error("Can`t delete TaskTaskTag. TaskTaskTag = " + taskTaskTag.toString() + ". " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    //TODO: Add task by tag and vice versa
}
