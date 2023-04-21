package com.application.dao.impl.JDBC;

import com.application.dao.interfaces.UserRoleDao;
import com.application.model.UserRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserRoleDao implements UserRoleDao {
    private final String GET_USER_ROLE_BY_ID = "select name from `user_role` where id = ?";
    private final String GET_ALL_USER_ROLES = "select id, name from `user_role`";
    private final String INSERT_USER_ROLE = "insert into `user_role`(`name`) values (?)";
    private final String UPDATE_USER_ROLE = "update `user_role` set name = ? where id = ?";
    private final String DELETE_USER_ROLE = "delete from `user_role` where id = ?";

    @Override
    public UserRole get(Connection connection, long id) {
        try (PreparedStatement get = connection.prepareStatement(GET_USER_ROLE_BY_ID))
        {
            get.setLong(1,id);
            ResultSet taskRole = get.executeQuery();
            if(taskRole.next())
            {
                String name = taskRole.getString(1);
                return new UserRole(id, name);
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
    public List<UserRole> getAll(Connection connection) {
        try (PreparedStatement getAll = connection.prepareStatement(GET_ALL_USER_ROLES))
        {
            ResultSet userRolesResult = getAll.executeQuery();
            List<UserRole> userRoles = new ArrayList<UserRole>();
            while(userRolesResult.next())
            {
                long id = userRolesResult.getLong(1);
                String name = userRolesResult.getString(2);
                userRoles.add(new UserRole(id,name));
            }
            return userRoles;
        }
        catch (SQLException e)
        {
            //TODO: add logging
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserRole save(Connection connection, UserRole userRole) {
        try (PreparedStatement save = connection.prepareStatement(INSERT_USER_ROLE, Statement.RETURN_GENERATED_KEYS))
        {
            save.setString(1,userRole.getName());
            save.executeUpdate();
            ResultSet id = save.getGeneratedKeys();
            long newId = 0;
            if (id.next()) {
                newId = id.getLong(1);
            }
            return new UserRole(newId, userRole.getName());
        }
        catch (SQLException e)
        {
            //TODO: add logging
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Connection connection, UserRole userRole) {
        try (PreparedStatement update = connection.prepareStatement(UPDATE_USER_ROLE))
        {
            update.setString(1,userRole.getName());
            update.setLong(2,userRole.getId());
            update.executeUpdate();
        }
        catch (SQLException e)
        {
            //TODO: add logging
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Connection connection, long id) {
        try (PreparedStatement delete = connection.prepareStatement(DELETE_USER_ROLE))
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
}
