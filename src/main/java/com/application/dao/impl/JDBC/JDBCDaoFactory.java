package com.application.dao.impl.JDBC;

import com.application.dao.impl.DaoFactory;
import com.application.dao.interfaces.*;

import java.sql.Connection;

public class JDBCDaoFactory extends DaoFactory {
    public JDBCDaoFactory(Connection connection) { super(connection); }
    @Override
    protected TaskDao createTaskDao() {
        return new JDBCTaskDao(connection);
    }

    @Override
    protected TaskRoleDao createTaskRoleDao() {
        return new JDBCTaskRoleDao(connection);
    }

    @Override
    protected TaskStatusDao createTaskStatusDao() {
        return new JDBCTaskStatusDao(connection);
    }

    @Override
    protected TaskTagDao createTaskTagDao() {
        return new JDBCTaskTagDao(connection);
    }

    @Override
    protected TaskTaskTagDao createTaskTaskTagDao() {
        return new JDBCTaskTaskTag(connection);
    }

    @Override
    protected TaskUserDao createTaskUserDao() {
        return new JDBCTaskUserDao(connection);
    }

    @Override
    protected UserDao createUserDao() {
        return new JDBCUserDao(connection);
    }

    @Override
    protected UserRoleDao createUserRoleDao() {
        return new JDBCUserRoleDao(connection);
    }
}
