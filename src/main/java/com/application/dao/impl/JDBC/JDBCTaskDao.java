package com.application.dao.impl.JDBC;

import com.application.dao.interfaces.TaskDao;
import com.application.model.*;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

class JDBCTaskDao implements TaskDao {
    private final Connection connection;
    private final String GET_TASK_BY_ID = "select t.name, t.description, t.creation_date, t.deadline_date, ts.id as status_id, ts.name as status_name, ts.description as status_descripton, t.priority" +
            " from `task` as t join `task_status` as ts on t.task_status_id = ts.id where t.id = ?";
    private final String GET_ALL_TASKS = "select t.id, t.name, t.description, t.creation_date, t.deadline_date, ts.id as status_id, ts.name as status_name, ts.description as status_descripton, t.priority" +
            " from `task` as t join `task_status` as ts on t.task_status_id = ts.id";
    private final String INSERT_TASK = "insert into `task`(`name`, `description`, `creation_date`, `deadline_date`, `task_status_id`, `priority`)" +
            " values (?, ?, ?, ?, ?, ?)";
    private final String UPDATE_TASK = "update `task` set name = ?, description = ?, creation_date = ?, deadline_date = ?, task_status_id = ?, priority = ? where id = ?";
    private final String DELETE_TASK = "delete form `task` where id = ?";
    private final String GET_TASKS_BY_USER = "select t.id, t.name, t.description, t.creation_date, t.deadline_date, ts.id, ts.name as status_name, ts.description, t.priority, tr.id, tr.name " +
            "from `task` as t join `task_status` as ts on t.task_status_id = ts.id join task_user as tu on tu.task_id = t.id join `user` as u on u.id = tu.user_id join task_role as tr on tr.id = tu.task_role_id where u.id = ?";
    public JDBCTaskDao(Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public Task get(long id) {
        try (PreparedStatement get = connection.prepareStatement(GET_TASK_BY_ID))
        {
            get.setLong(1,id);
            ResultSet tag = get.executeQuery();
            if(tag.next())
            {
                String name = tag.getString(1);
                String description = tag.getString(2);
                Instant creation_date = tag.getTimestamp(3).toInstant();
                Instant deadline_date = tag.getTimestamp(4).toInstant();
                long status_id = tag.getLong(5);
                String status_name = tag.getString(6);
                String status_description = tag.getString(7);
                short priority = tag.getShort(8);
                return new Task(id, name,description,creation_date,deadline_date,new TaskStatus(status_id,status_name,status_description),priority);
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
    public List<Task> getAll() {
        try (PreparedStatement getAll = connection.prepareStatement(GET_ALL_TASKS))
        {
            ResultSet tasksResult = getAll.executeQuery();
            List<Task> tasks = new ArrayList<Task>();
            while(tasksResult.next())
            {
                long id = tasksResult.getLong(1);
                String name = tasksResult.getString(2);
                String description = tasksResult.getString(3);
                Instant creation_date = tasksResult.getTimestamp(4).toInstant();
                Instant deadline_date = tasksResult.getTimestamp(5).toInstant();
                long status_id = tasksResult.getLong(6);
                String status_name = tasksResult.getString(7);
                String status_description = tasksResult.getString(8);
                short priority = tasksResult.getShort(9);
                tasks.add(new Task(id, name,description,creation_date,deadline_date,new TaskStatus(status_id,status_name,status_description),priority));
            }
            return tasks;
        }
        catch (SQLException e)
        {
            //TODO: add logging
            throw new RuntimeException(e);
        }
    }

    @Override
    public Task save(Task task) {
        try (PreparedStatement save = connection.prepareStatement(INSERT_TASK, Statement.RETURN_GENERATED_KEYS))
        {
            save.setString(1,task.getName());
            save.setString(2,task.getDescription());
            save.setTimestamp(3,java.sql.Timestamp.from(task.getCreationDate()));
            save.setTimestamp(4,java.sql.Timestamp.from(task.getDeadline()));
            save.setLong(5,task.getStatus().getId());
            save.setShort(6,task.getPriority());
            save.executeUpdate();
            ResultSet id = save.getGeneratedKeys();
            long newId = 0;
            if (id.next()) {
                newId = id.getLong(1);
            }
            return new Task(newId, task.getName(),task.getDescription(),task.getCreationDate(),task.getDeadline(),task.getStatus(),task.getPriority());
        }
        catch (SQLException e)
        {
            //TODO: add logging
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Task task) {
        try (PreparedStatement update = connection.prepareStatement(UPDATE_TASK))
        {
            update.setString(1,task.getName());
            update.setString(2,task.getDescription());
            update.setTimestamp(3,java.sql.Timestamp.from(task.getCreationDate()));
            update.setTimestamp(4,java.sql.Timestamp.from(task.getDeadline()));
            update.setLong(5,task.getStatus().getId());
            update.setShort(6,task.getPriority());
            update.setLong(7, task.getId());
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
        try (PreparedStatement delete = connection.prepareStatement(DELETE_TASK))
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

    @Override
    public List<TaskUser> getTasksByUser(User user) {
        try (PreparedStatement getTasksByUser = connection.prepareStatement(GET_TASKS_BY_USER))
        {
            getTasksByUser.setLong(1, user.getId());
            ResultSet tasksResult = getTasksByUser.executeQuery();
            List<TaskUser> tasks = new ArrayList<TaskUser>();
            while(tasksResult.next())
            {
                long id = tasksResult.getLong(1);
                String name = tasksResult.getString(2);
                String description = tasksResult.getString(3);
                Instant creation_date = tasksResult.getTimestamp(4).toInstant();
                Instant deadline_date = tasksResult.getTimestamp(5).toInstant();
                long status_id = tasksResult.getLong(6);
                String status_name = tasksResult.getString(7);
                String status_description = tasksResult.getString(8);
                short priority = tasksResult.getShort(9);
                long taskRoleId = tasksResult.getLong(10);
                String taskRoleName = tasksResult.getString(11);
                TaskStatus taskStatus = new TaskStatus(status_id,status_name,status_description);
                Task task = new Task(id, name,description,creation_date,deadline_date, taskStatus, priority);
                TaskRole taskRole = new TaskRole(taskRoleId, taskRoleName);
                tasks.add(new TaskUser(task,user,taskRole));
            }
            return tasks;
        }
        catch (SQLException e)
        {
            //TODO: add logging
            throw new RuntimeException(e);
        }
    }
}
