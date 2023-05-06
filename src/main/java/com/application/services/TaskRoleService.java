package com.application.services;

import com.application.dao.ConnectionPool;
import com.application.dao.DaoFactory;
import com.application.model.TaskRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class TaskRoleService {
    public static final Logger LOGGER = LogManager.getLogger(TaskRoleService.class);
    private final DaoFactory daoFactory;
    public TaskRoleService(DaoFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }
    public TaskRole getTaskRole(long id) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try{
            return daoFactory.getTaskRoleDao().get(con, id);
        }
        catch (Exception e){
            LOGGER.error("Error: " + e.getMessage());
            throw new RuntimeException("Error while getting taskRole");
        }
        finally {
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }
    public List<TaskRole> getAllTaskRoles(){
        Connection con = ConnectionPool.getInstance().getConnection();
        try {
            return daoFactory.getTaskRoleDao().getAll(con);
        }
        catch (Exception e){
            LOGGER.error("Error : " + e.getMessage());
            throw new RuntimeException("Error while getting all tasks");
        }
        finally {
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }
}
