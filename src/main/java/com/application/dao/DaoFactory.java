package com.application.dao;

import com.application.dao.ConnectionPool;
import com.application.dao.interfaces.*;
import com.application.model.TaskTaskTag;
import com.application.model.UserRole;

import java.sql.Connection;
import java.util.function.Function;

public abstract class DaoFactory {
    private TaskDao taskDao;
    private TaskRoleDao taskRoleDao;
    private TaskStatusDao taskStatusDao;
    private TaskTagDao taskTagDao;
    private TaskTaskTagDao taskTaskTag;
    private TaskUserDao taskUserDao;
    private UserDao userDao;
    private UserRoleDao userRoleDao;
    public TaskDao getTaskDao() {
        if(taskDao == null)
        {
            taskDao = createTaskDao();
        }
        return taskDao;
    }
    protected abstract TaskDao createTaskDao();
    public TaskRoleDao getTaskRoleDao() {
        if(taskRoleDao == null)
        {
            taskRoleDao = createTaskRoleDao();
        }
        return taskRoleDao;
    }
    protected abstract TaskRoleDao createTaskRoleDao();
    public TaskStatusDao getTaskStatusDao() {
        if(taskStatusDao == null)
        {
            taskStatusDao = createTaskStatusDao();
        }
        return taskStatusDao;
    }
    protected abstract TaskStatusDao createTaskStatusDao();
    public TaskTagDao getTaskTagDao() {
        if(taskTagDao == null)
        {
            taskTagDao = createTaskTagDao();
        }
        return taskTagDao;
    }
    protected abstract TaskTagDao createTaskTagDao();
    public TaskTaskTagDao getTaskTaskTagDao() {
        if(taskTaskTag == null)
        {
            taskTaskTag = createTaskTaskTagDao();
        }
        return taskTaskTag;
    }
    protected abstract TaskTaskTagDao createTaskTaskTagDao();
    public TaskUserDao getTaskUserDao() {
        if(taskUserDao == null)
        {
            taskUserDao = createTaskUserDao();
        }
        return taskUserDao;
    }
    protected abstract TaskUserDao createTaskUserDao();
    public UserDao getUserDao() {
        if(userDao == null)
        {
            userDao = createUserDao();
        }
        return userDao;
    }
    protected abstract UserDao createUserDao();
    public UserRoleDao getUserRoleDao() {
        if(userRoleDao == null)
        {
            userRoleDao = createUserRoleDao();
        }
        return userRoleDao;
    }
    protected abstract UserRoleDao createUserRoleDao();

}
