package com.application.services;

import com.application.dao.ConnectionPool;
import com.application.dao.DaoFactory;
import com.application.model.Task;
import com.application.model.TaskRole;
import com.application.model.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class UserRoleService {
    private static final Logger LOGGER = LogManager.getLogger(UserRoleService.class);
    public final DaoFactory daoFactory;
    public UserRoleService(DaoFactory daoFactory){
        this.daoFactory = daoFactory;
    }
    public UserRole getUserRole(long id) {
        
        Connection con = ConnectionPool.getInstance().getConnection();
        try{            
            return daoFactory.getUserRoleDao().get(con, id);
        }
        catch (Exception e){
            LOGGER.error("Error: " + e.getMessage());
            throw new RuntimeException("Error while getting task");
        }
        finally {
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }
    public List<UserRole> getAllUserRoles(){
        
        Connection con = ConnectionPool.getInstance().getConnection();
        try{            
            return daoFactory.getUserRoleDao().getAll(con);
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
