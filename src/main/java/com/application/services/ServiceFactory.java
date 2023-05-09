package com.application.services;

import com.application.dao.DaoFactory;

public class ServiceFactory {
    private final DaoFactory daoFactory;
    public ServiceFactory(DaoFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }
    public UserService getUserService()
    {
        return new UserService(daoFactory);
    }
    public TaskService getTaskService()
    {
        return new TaskService(daoFactory);
    }
    public UserRoleService getUserRoleService()
    {
        return new UserRoleService(daoFactory);
    }
    public TaskStatusService getTaskStatusService()
    {
        return new TaskStatusService(daoFactory);
    }
}
