package com.application.services;

import com.application.dao.ConnectionPool;
import com.application.dao.DaoFactory;
import com.application.model.TaskStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class TaskStatusService {
    private static final Logger LOGGER = LogManager.getLogger(TaskStatusService.class);
    private final DaoFactory daoFactory;
    public TaskStatusService(DaoFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }
    public TaskStatus getTaskStatus(long id) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try{            
            return daoFactory.getTaskStatusDao().get(con, id);
        }
        catch (Exception e){
            LOGGER.error("Error: " + e.getMessage());
            throw new RuntimeException("Error while getting TaskStatus");
        }
        finally {
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }
    public List<TaskStatus> getAllTaskStatuses(){
        
        Connection con = ConnectionPool.getInstance().getConnection();
        try{            
            return daoFactory.getTaskStatusDao().getAll(con);
        }
        catch (Exception e){
            LOGGER.error("Error : " + e.getMessage());
            throw new RuntimeException("Error while getting all TaskStatuses");
        }
        finally {
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }
    public void updateTaskStatus(TaskStatus taskStatus){
        
        Connection con = ConnectionPool.getInstance().getConnection();
        try{            
            try {
                con.setAutoCommit(false);
                daoFactory.getTaskStatusDao().update(con, taskStatus);
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
            throw new RuntimeException("Error while update taskStatus");
        }
        finally {
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }
    public TaskStatus createTaskStatus(TaskStatus taskStatus){
        
        Connection con = ConnectionPool.getInstance().getConnection();
        try{            
            try {
                con.setAutoCommit(false);
                TaskStatus newTaskStatus = daoFactory.getTaskStatusDao().save(con, taskStatus);
                con.commit();
                return newTaskStatus;
            } catch (Exception e) {
                try {
                    con.rollback();
                } catch (Exception ex){
                    LOGGER.error("Error while rollback : " + ex.getMessage());
                }
                throw new RuntimeException("Error while create TaskStatus");
            } finally {
                con.setAutoCommit(true);
            }
        } catch (Exception e){
            LOGGER.error("Error: " + e.getMessage());
            throw new RuntimeException("Error while create TaskStatus");
        }
        finally {
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }
    //TODO: Add task by status and vice versa
}
