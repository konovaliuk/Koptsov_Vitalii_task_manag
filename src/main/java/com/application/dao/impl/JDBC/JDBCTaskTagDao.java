package com.application.dao.impl.JDBC;

import com.application.dao.interfaces.TaskTagDao;
import com.application.model.TaskTag;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCTaskTagDao implements TaskTagDao {
    private final Connection connection;
    private final String GET_TAG_BY_ID = "select name, description from `task_tag` where id = ?";
    private final String GET_ALL_TAGS = "select id, name, description from `task_tag`";
    private final String INSERT_TAG = "insert into `task_tag`(`name`,`description`) values (?, ?)";
    private final String UPDATE_TAG = "update `task_tag` set name = ?, description = ? where id = ?";
    private final String DELETE_TAG = "delete from `task_tag` where id = ?";

    public JDBCTaskTagDao(Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public TaskTag get(long id) {
        try (PreparedStatement get = connection.prepareStatement(GET_TAG_BY_ID))
        {
            get.setLong(1,id);
            ResultSet tag = get.executeQuery();
            if(tag.next())
            {
                String name = tag.getString(1);
                String description = tag.getString(2);
                return new TaskTag(id,name,description);
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
    public List<TaskTag> getAll() {
        try (PreparedStatement getAll = connection.prepareStatement(GET_ALL_TAGS))
        {
            ResultSet tags = getAll.executeQuery();
            List<TaskTag> taskTags = new ArrayList<TaskTag>();
            while(tags.next())
            {
                long id = tags.getLong(1);
                String name = tags.getString(2);
                String description = tags.getString(3);
                taskTags.add(new TaskTag(id,name,description));
            }
            return taskTags;
        }
        catch (SQLException e)
        {
            //TODO: add logging
            throw new RuntimeException(e);
        }
    }

    @Override
    public TaskTag save(TaskTag taskTag) {
        try (PreparedStatement save = connection.prepareStatement(INSERT_TAG, Statement.RETURN_GENERATED_KEYS))
        {
            save.setString(1,taskTag.getName());
            save.setString(2,taskTag.getDescription());
            save.executeUpdate();
            ResultSet id = save.getGeneratedKeys();
            long newId = 0;
            if (id.next()) {
                newId = id.getLong(1);
            }
            return new TaskTag(newId, taskTag.getName(),taskTag.getDescription());
        }
        catch (SQLException e)
        {
            //TODO: add logging
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(TaskTag taskTag) {
        try (PreparedStatement update = connection.prepareStatement(UPDATE_TAG))
        {
            update.setString(1,taskTag.getName());
            update.setString(2,taskTag.getDescription());
            update.setLong(3,taskTag.getId());
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
        try (PreparedStatement delete = connection.prepareStatement(DELETE_TAG))
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
