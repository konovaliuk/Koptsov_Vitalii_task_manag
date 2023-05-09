package com.application.dao.impl.JDBC;

import com.application.dao.interfaces.TaskTagDao;
import com.application.model.*;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JDBCTaskTagDao implements TaskTagDao {
    private static final Logger LOGGER = LogManager.getLogger(JDBCTaskTagDao.class);
    private final String GET_TAG_BY_ID = "select name, description from `task_tag` where id = ?";
    private final String GET_ALL_TAGS = "select id, name, description from `task_tag`";
    private final String INSERT_TAG = "insert into `task_tag`(`name`,`description`) values (?, ?)";
    private final String UPDATE_TAG = "update `task_tag` set name = ?, description = ? where id = ?";
    private final String DELETE_TAG = "delete from `task_tag` where id = ?";
    private final String GET_TASK_TAGS_BY_TASK = "select tt.id,tt.name,tt.description " +
            "from task_tag as tt join task_tag_task as ttt on ttt.task_tag_id = tt.id join task as t on ttt.task_id = t.id where t.id = ?";
    @Override
    public TaskTag get(Connection connection, long id) {
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
            LOGGER.error("Can`t get TaskTag by id. Id = " + id + ". " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TaskTag> getAll(Connection connection) {
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
            LOGGER.error("Can`t get all TaskTags. " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public TaskTag save(Connection connection, TaskTag taskTag) {
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
            LOGGER.error("Can`t save TaskTag. TaskTag = " + taskTag.toString() + ". " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Connection connection, TaskTag taskTag) {
        try (PreparedStatement update = connection.prepareStatement(UPDATE_TAG))
        {
            update.setString(1,taskTag.getName());
            update.setString(2,taskTag.getDescription());
            update.setLong(3,taskTag.getId());
            update.executeUpdate();
        }
        catch (SQLException e)
        {
            LOGGER.error("Can`t update TaskTag. TaskTag = " + taskTag.toString() + ". " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Connection connection, long id) {
        try (PreparedStatement delete = connection.prepareStatement(DELETE_TAG))
        {
            delete.setLong(1,id);
            delete.executeUpdate();
        }
        catch (SQLException e)
        {
            LOGGER.error("Can`t delete TaskTag. Id = " + id + ". " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<TaskTag> getTaskTagsByTask(Connection connection, Task task)
    {
        try (PreparedStatement getTasksByUser = connection.prepareStatement(GET_TASK_TAGS_BY_TASK))
        {
            getTasksByUser.setLong(1, task.getId());
            ResultSet tasksResult = getTasksByUser.executeQuery();
            List<TaskTag> taskTags = new ArrayList<TaskTag>();
            while(tasksResult.next())
            {
                long id = tasksResult.getLong(1);
                String name = tasksResult.getString(2);
                String description = tasksResult.getString(3);
                TaskTag tag = new TaskTag(id,name,description);
                taskTags.add(tag);
            }
            return taskTags;
        }
        catch (SQLException e)
        {
            LOGGER.error("Can`t ger all TaskTags for Task. Task = " + task.toString() + ". " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
