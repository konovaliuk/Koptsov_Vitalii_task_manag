package com.application.services;

import com.application.dao.ConnectionPool;
import com.application.dao.DaoFactory;
import com.application.model.Task;
import com.application.model.TaskRole;
import com.application.model.TaskUser;
import com.application.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

public class TaskService {
    private static final Logger LOGGER = LogManager.getLogger(TaskService.class);
    private final DaoFactory daoFactory;
    public TaskService(DaoFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }
    //TODO: delete task
    public Task getTask(long id) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try{
            return daoFactory.getTaskDao().get(con, id);
        }
        catch (Exception e){
            LOGGER.error("Error: " + e.getMessage());
            throw new RuntimeException("Error while getting task");
        }
        finally {
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }

    public Task getFullTask(long id) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try{
            Task task = daoFactory.getTaskDao().get(con, id);
            task.setTags(daoFactory.getTaskTagDao().getTaskTagsByTask(con, task));
            task.setTaskUsers(daoFactory.getUserDao().getUsersByTask(con,task));
            return task;
        }
        catch (Exception e){
            LOGGER.error("Error: " + e.getMessage());
            throw new RuntimeException("Error while getting task");
        }
        finally {
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }
    public List<Task> getAllTasks(){
        Connection con = ConnectionPool.getInstance().getConnection();
        try{
            return daoFactory.getTaskDao().getAll(con);
        }
        catch (Exception e){
            LOGGER.error("Error : " + e.getMessage());
            throw new RuntimeException("Error while getting all tasks");
        }
        finally {
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }
    public void updateTask(Task task){
        Connection con = ConnectionPool.getInstance().getConnection();
        try{
            try {
                con.setAutoCommit(false);
                daoFactory.getTaskDao().update(con, task);
                con.commit();
            } catch (Exception e) {
                try {
                    con.rollback();
                } catch (Exception ex){
                    LOGGER.error("Error while rollback : " + ex.getMessage());
                }
            } finally {
                con.setAutoCommit(true);
            }
        } catch (Exception e){
            LOGGER.error("Error: " + e.getMessage());
            throw new RuntimeException("Error while update task");
        }
        finally {
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }
    public Task createTask(Task task){
        Connection con = ConnectionPool.getInstance().getConnection();
        try{
            try {
                con.setAutoCommit(false);
                Task newTask = daoFactory.getTaskDao().save(con, task);
                con.commit();
                return newTask;
            } catch (Exception e) {
                try {
                    con.rollback();
                } catch (Exception ex){
                    LOGGER.error("Error while rollback : " + ex.getMessage());
                }
                throw new RuntimeException("Error while create task");
            } finally {
                con.setAutoCommit(true);
            }
        } catch (Exception e){
            LOGGER.error("Error: " + e.getMessage());
            throw new RuntimeException("Error while create task");
        }
        finally {
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }
    public List<Task> getAllTasksAssignedOnUser(User user)
    {
        Connection con = ConnectionPool.getInstance().getConnection();
        try{
            List<TaskUser> userTasksWithRole = daoFactory.getTaskDao().getTasksByUser(con, user);
            return userTasksWithRole.stream().map(TaskUser::getTask).collect(Collectors.toList());
        }
        catch (Exception e){
            LOGGER.error("Error : " + e.getMessage());
            throw new RuntimeException("Error while getting all tasks on user");
        }
        finally {
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }
    public void assignUserToTask(User user, Task task, TaskRole taskRole) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try{
            try {
                con.setAutoCommit(false);
                TaskUser taskUser = new TaskUser(task,user,taskRole);
                daoFactory.getTaskUserDao().save(con, taskUser);
                con.commit();
            } catch (Exception e) {
                try {
                    con.rollback();
                } catch (Exception ex){
                    LOGGER.error("Error while rollback : " + ex.getMessage());
                }
                throw new RuntimeException("Error while assign user to task");
            } finally {
                con.setAutoCommit(true);
            }
        } catch (Exception e){
            LOGGER.error("Error: " + e.getMessage());
            throw new RuntimeException("Error while assign user to task");
        }
        finally {
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }
    public void releaseUserFromTask(User user, Task task) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try{
            try {
                con.setAutoCommit(false);
                TaskUser taskUser = new TaskUser(task,user, new TaskRole(-1,"no role"));
                daoFactory.getTaskUserDao().delete(con, taskUser);
                con.commit();
            } catch (Exception e) {
                try {
                    con.rollback();
                } catch (Exception ex){
                    LOGGER.error("Error while rollback : " + ex.getMessage());
                }
                throw new RuntimeException("Error while release user from task");
            } finally {
                con.setAutoCommit(true);
            }
        } catch (Exception e){
            LOGGER.error("Error: " + e.getMessage());
            throw new RuntimeException("Error while release user from task");
        }
        finally {
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }
    public void changeUserRoleToTask(User user, Task task, TaskRole taskRole){
        Connection con = ConnectionPool.getInstance().getConnection();
        try{
            try {
                con.setAutoCommit(false);
                TaskUser taskUser = new TaskUser(task,user,taskRole);
                daoFactory.getTaskUserDao().updateTaskRole(con, taskUser);
                con.commit();
            } catch (Exception e) {
                try {
                    con.rollback();
                } catch (Exception ex){
                    LOGGER.error("Error while rollback : " + ex.getMessage());
                }
                throw new RuntimeException("Error while changing user role to task");
            } finally {
                con.setAutoCommit(true);
            }
        } catch (Exception e){
            LOGGER.error("Error: " + e.getMessage());
            throw new RuntimeException("Error while changing user role to task");
        }
        finally {
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }

}
