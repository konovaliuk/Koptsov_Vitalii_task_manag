package com.application.services;

import com.application.dao.ConnectionPool;
import com.application.dao.DaoFactory;
import com.application.model.TaskTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class TaskTagService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);
    private final DaoFactory daoFactory;
    public TaskTagService(DaoFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }
    public TaskTag getTaskTag(long id) {
        
        Connection con = ConnectionPool.getInstance().getConnection();
        try{            
            return daoFactory.getTaskTagDao().get(con, id);
        }
        catch (Exception e){
            LOGGER.error("Error: " + e.getMessage());
            throw new RuntimeException("Error while getting TaskTag");
        }
        finally {
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }
    public List<TaskTag> getAllTaskTags(){
        
        Connection con = ConnectionPool.getInstance().getConnection();
        try{            
            return daoFactory.getTaskTagDao().getAll(con);
        }
        catch (Exception e){
            LOGGER.error("Error : " + e.getMessage());
            throw new RuntimeException("Error while getting all TaskTags");
        }
        finally {
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }
    public void updateTaskTag(TaskTag TaskTag){
        
        Connection con = ConnectionPool.getInstance().getConnection();
        try{            
            try {
                con.setAutoCommit(false);
                daoFactory.getTaskTagDao().update(con, TaskTag);
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
            throw new RuntimeException("Error while update taskTag");
        }
        finally {
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }
    public TaskTag createTaskTag(TaskTag TaskTag){
        
        Connection con = ConnectionPool.getInstance().getConnection();
        try{            
            try {
                con.setAutoCommit(false);
                TaskTag newTaskTag = daoFactory.getTaskTagDao().save(con, TaskTag);
                con.commit();
                return newTaskTag;
            } catch (Exception e) {
                try {
                    con.rollback();
                } catch (Exception ex){
                    LOGGER.error("Error while rollback : " + ex.getMessage());
                }
                throw new RuntimeException("Error while create TaskTag");
            } finally {
                con.setAutoCommit(true);
            }
        } catch (Exception e){
            LOGGER.error("Error: " + e.getMessage());
            throw new RuntimeException("Error while create TaskTag");
        }
        finally {
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }
    //TODO: Add task by tag and vice versa
}
