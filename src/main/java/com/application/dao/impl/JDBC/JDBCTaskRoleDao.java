package com.application.dao.impl.JDBC;

import com.application.dao.interfaces.TaskRoleDao;
import com.application.model.TaskRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JDBCTaskRoleDao implements TaskRoleDao {
    private static final Logger LOGGER = LogManager.getLogger(JDBCTaskRoleDao.class);
    private final String GET_TASK_ROLE_BY_ID = "select name from `task_role` where id = ?";
    private final String GET_ALL_TASK_ROLES = "select id, name from `task_role`";
    private final String INSERT_TASK_ROLE = "insert into `task_role`(`name`) values (?)";
    private final String UPDATE_TASK_ROLE = "update `task_role` set name = ? where id = ?";
    private final String DELETE_TASK_ROLE = "delete from `task_role` where id = ?";

    @Override
    public TaskRole get(Connection connection, long id) {
        try (PreparedStatement get = connection.prepareStatement(GET_TASK_ROLE_BY_ID))
        {
            get.setLong(1,id);
            ResultSet taskRole = get.executeQuery();
            if(taskRole.next())
            {
                String name = taskRole.getString(1);
                return new TaskRole(id, name);
            }
            return null;
        }
        catch (SQLException e)
        {
            LOGGER.error("Can`t get TaskRole by id. Id = " + id + ". " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TaskRole> getAll(Connection connection) {
        try (PreparedStatement getAll = connection.prepareStatement(GET_ALL_TASK_ROLES))
        {
            ResultSet taskRolesResult = getAll.executeQuery();
            List<TaskRole> taskRoles = new ArrayList<TaskRole>();
            while(taskRolesResult.next())
            {
                long id = taskRolesResult.getLong(1);
                String name = taskRolesResult.getString(2);
                taskRoles.add(new TaskRole(id,name));
            }
            return taskRoles;
        }
        catch (SQLException e)
        {
            LOGGER.error("Can`t get all TaskRoles. " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public TaskRole save(Connection connection, TaskRole taskRole) {
        try (PreparedStatement save = connection.prepareStatement(INSERT_TASK_ROLE, Statement.RETURN_GENERATED_KEYS))
        {
            save.setString(1,taskRole.getName());
            save.executeUpdate();
            ResultSet id = save.getGeneratedKeys();
            long newId = 0;
            if (id.next()) {
                newId = id.getLong(1);
            }
            return new TaskRole(newId, taskRole.getName());
        }
        catch (SQLException e)
        {
            LOGGER.error("Can`t save TaskRole. TaskRole = " + taskRole.toString() + ". " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Connection connection, TaskRole taskRole) {
        try (PreparedStatement update = connection.prepareStatement(UPDATE_TASK_ROLE))
        {
            update.setString(1,taskRole.getName());
            update.setLong(2,taskRole.getId());
            update.executeUpdate();
        }
        catch (SQLException e)
        {
            LOGGER.error("Can`t update TaskRole. TaskRole = " + taskRole.toString() + ". " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Connection connection, long id) {
        try (PreparedStatement delete = connection.prepareStatement(DELETE_TASK_ROLE))
        {
            delete.setLong(1,id);
            delete.executeUpdate();
        }
        catch (SQLException e)
        {
            LOGGER.error("Can`t delete TaskRole. Id = " + id + ". " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
