package com.application.dao.impl.JDBC;

import com.application.dao.interfaces.TaskUserDao;
import com.application.model.TaskUser;

import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JDBCTaskUserDao implements TaskUserDao {
    private static final Logger LOGGER = LogManager.getLogger(JDBCTaskUserDao.class);
    private final String INSERT_TASK_USER = "insert into `task_user`(user_id, task_id, task_role_id) values (?, ?, ?)";
    private final String UPDATE_TASK_ROLE = "update `task_user` set task_role_id=? where task_id = ? and user_id = ?";
    private final String DELETE_TASK_USER = "delete from `task_user` where task_id = ? and user_id = ?";

    @Override
    public void save(Connection connection, TaskUser taskUser) {
        try (PreparedStatement save = connection.prepareStatement(INSERT_TASK_USER))
        {
            save.setLong(1, taskUser.getUser().getId());
            save.setLong(2, taskUser.getTask().getId());
            save.setLong(3, taskUser.getTaskRole().getId());
            save.executeUpdate();
        }
        catch (SQLException e)
        {
            LOGGER.error("Can`t save TaskUser. TaskUser = " + taskUser.toString() + ". " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateTaskRole(Connection connection, TaskUser taskUser) {
        try (PreparedStatement update = connection.prepareStatement(UPDATE_TASK_ROLE))
        {
            update.setLong(1, taskUser.getTaskRole().getId());
            update.setLong(2, taskUser.getTask().getId());
            update.setLong(3, taskUser.getUser().getId());
            update.executeUpdate();
        }
        catch (SQLException e)
        {
            LOGGER.error("Can`t update TaskUser. TaskUser = " + taskUser.toString() + ". " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Connection connection, TaskUser taskUser) {
        try (PreparedStatement delete = connection.prepareStatement(DELETE_TASK_USER))
        {
            delete.setLong(1, taskUser.getTask().getId());
            delete.setLong(2, taskUser.getUser().getId());
            delete.executeUpdate();
        }
        catch (SQLException e)
        {
            LOGGER.error("Can`t delete TaskTag. TaskUser = " + taskUser.toString() + ". " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
