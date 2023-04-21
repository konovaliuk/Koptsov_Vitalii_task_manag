package com.application.dao.impl.JDBC;

import com.application.dao.DaoFactory;
import com.application.dao.interfaces.*;

import java.sql.Connection;

public class JDBCDaoFactory extends DaoFactory {
    public JDBCDaoFactory() { super(); }
    @Override
    protected TaskDao createTaskDao() {
        return new JDBCTaskDao();
    }

    @Override
    protected TaskRoleDao createTaskRoleDao() {
        return new JDBCTaskRoleDao();
    }

    @Override
    protected TaskStatusDao createTaskStatusDao() {
        return new JDBCTaskStatusDao();
    }

    @Override
    protected TaskTagDao createTaskTagDao() {
        return new JDBCTaskTagDao();
    }

    @Override
    protected TaskTaskTagDao createTaskTaskTagDao() {
        return new JDBCTaskTaskTag();
    }

    @Override
    protected TaskUserDao createTaskUserDao() {
        return new JDBCTaskUserDao();
    }

    @Override
    protected UserDao createUserDao() {
        return new JDBCUserDao();
    }

    @Override
    protected UserRoleDao createUserRoleDao() {
        return new JDBCUserRoleDao();
    }
}
