package com.application.dao.impl.JDBC;

import com.application.dao.interfaces.TaskStatusDao;
import com.application.model.TaskStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCTaskStatusDao implements TaskStatusDao {
    private final Connection connection;
    private final String GET_STATUS_BY_ID = "select name, description from `task_status` where id = ?";
    private final String GET_ALL_STATUSES = "select id, name, description from `task_status`";
    private final String INSERT_STATUS = "insert into `task_status`(`name`,`description`) values (?, ?)";
    private final String UPDATE_STATUS = "update `task_status` set name = ?, description = ? where id = ?";
    private final String DELETE_STATUS = "delete from `task_status` where id = ?";

    public JDBCTaskStatusDao(Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public TaskStatus get(long id) {
        try (PreparedStatement get = connection.prepareStatement(GET_STATUS_BY_ID))
        {
            get.setLong(1,id);
            ResultSet tag = get.executeQuery();
            if(tag.next())
            {
                String name = tag.getString(1);
                String description = tag.getString(2);
                return new TaskStatus(id,name,description);
            }
            return null;
        }
        catch (SQLException e)
        {
            //TODO: add logging
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TaskStatus> getAll() {
        try (PreparedStatement getAll = connection.prepareStatement(GET_ALL_STATUSES))
        {
            ResultSet tags = getAll.executeQuery();
            List<TaskStatus> taskStatuses = new ArrayList<TaskStatus>();
            while(tags.next())
            {
                long id = tags.getLong(1);
                String name = tags.getString(2);
                String description = tags.getString(3);
                taskStatuses.add(new TaskStatus(id,name,description));
            }
            return taskStatuses;
        }
        catch (SQLException e)
        {
            //TODO: add logging
            throw new RuntimeException(e);
        }
    }

    @Override
    public TaskStatus save(TaskStatus taskStatus) {
        try (PreparedStatement save = connection.prepareStatement(INSERT_STATUS, Statement.RETURN_GENERATED_KEYS))
        {
            save.setString(1,taskStatus.getName());
            save.setString(2,taskStatus.getDescription());
            save.executeUpdate();
            ResultSet id = save.getGeneratedKeys();
            long newId = 0;
            if (id.next()) {
                newId = id.getLong(1);
            }
            return new TaskStatus(newId, taskStatus.getName(),taskStatus.getDescription());
        }
        catch (SQLException e)
        {
            //TODO: add logging
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(TaskStatus taskStatus) {
        try (PreparedStatement update = connection.prepareStatement(UPDATE_STATUS))
        {
            update.setString(1,taskStatus.getName());
            update.setString(2,taskStatus.getDescription());
            update.setLong(3,taskStatus.getId());
            update.executeUpdate();
        }
        catch (SQLException e)
        {
            //TODO: add logging
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(long id) {
        try (PreparedStatement delete = connection.prepareStatement(DELETE_STATUS))
        {
            delete.setLong(1,id);
            delete.executeUpdate();
        }
        catch (SQLException e)
        {
            //TODO: add logging
            throw new RuntimeException(e);
        }

    }
}
