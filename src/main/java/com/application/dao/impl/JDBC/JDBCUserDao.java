package com.application.dao.impl.JDBC;

import com.application.dao.interfaces.UserDao;
import com.application.model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserDao implements UserDao {
    private final Connection connection;
    private final String GET_USER_BY_ID = "select u.first_name, u.last_name, u.middle_name, u.telegram_tag, u.faculty, u.group, u.email, u.phone_number, u.day_of_birth, u.day_of_admission, r.id, r.name" +
            " from `user` as u join `user_role` as r on u.user_role_id = r.id where u.id = ?";
    private final String GET_LOGIN_INFO = "select id, first_name, last_name, password from `user` where login = ? or email = ?";
    private final String UPDATE_LOGIN_INFO = "update `user` set login = ?, password = ? where id = ?";
    private final String GET_ALL_USERS = "select u.id, u.first_name, u.last_name, u.middle_name, u.telegram_tag, u.faculty, u.group, u.email, u.phone_number, u.day_of_birth, u.day_of_admission, r.id, r.name" +
            " from `user` as u join `user_role` as r on u.user_role_id = r.id";
    private final String INSERT_USER = "insert into `user`(`first_name`,`last_name`,`middle_name`, `telegram_tag`, `faculty`, `group`, `email`, `phone_number`, `day_of_birth`, `day_of_admission`, `user_role_id`)" +
            " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE_USER = "update `user` set first_name = ?, last_name = ?, middle_name = ?, telegram_tag=?, faculty=?,group=?,email=?,phone_number=?,day_of_birth=?,day_of_admission=?, user_role_id = ?" +
            " where id = ?";
    private final String DELETE_USER = "delete from `user` where id = ?";
    private final String GET_USERS_BY_TASK = "select u.id, u.first_name, u.last_name, u.middle_name, u.telegram_tag, u.faculty, u.group, u.email, u.phone_number, u.day_of_birth, u.day_of_admission, r.id, r.name" +
            " from `user` as u join `user_role` as r on u.user_role_id = r.id join task_user as tu on tu.user_id = u.id join task as t on t.id = tu.task_id where t.id = ?";
    private final String GET_USER_BY_USER_ROLE = "select u.id, u.first_name, u.last_name, u.middle_name, u.telegram_tag, u.faculty, u.group, u.email, u.phone_number, u.day_of_birth, u.day_of_admission" +
            " from `user` as u join `user_role` as r on u.user_role_id = r.id where r.id = ?";
    public JDBCUserDao(Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public User get(long id) {
        try (PreparedStatement get = connection.prepareStatement(GET_USER_BY_ID))
        {
            get.setLong(1,id);
            ResultSet user = get.executeQuery();
            if(user.next())
            {
                String firstName = user.getString(1);
                String lastName = user.getString(2);
                String middleName = user.getString(3);
                String telegramTag = user.getString(4);
                String faculty = user.getString(5);
                String group = user.getString(6);
                String email = user.getString(7);
                String phoneNumber = user.getString(8);
                LocalDate birthday = user.getDate(9).toLocalDate();
                LocalDate admissionDay = user.getDate(10).toLocalDate();
                long role_id = user.getLong(11);
                String role_name = user.getString(12);
                return new User(id, firstName,lastName,middleName,telegramTag,faculty,group,email,phoneNumber,birthday,admissionDay, new UserRole(role_id,role_name));
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
    public List<User> getAll() {
        try (PreparedStatement getAll = connection.prepareStatement(GET_ALL_USERS))
        {
            ResultSet userResult = getAll.executeQuery();
            List<User> users = new ArrayList<User>();
            while(userResult.next())
            {
                long id = userResult.getLong(1);
                String firstName = userResult.getString(2);
                String lastName = userResult.getString(3);
                String middleName = userResult.getString(4);
                String telegramTag = userResult.getString(5);
                String faculty = userResult.getString(6);
                String group = userResult.getString(7);
                String email = userResult.getString(8);
                String phoneNumber = userResult.getString(9);
                LocalDate birthday = userResult.getDate(10).toLocalDate();
                LocalDate admissionDay = userResult.getDate(11).toLocalDate();
                long role_id = userResult.getLong(12);
                String role_name = userResult.getString(13);
                users.add(new User(id, firstName,lastName,middleName,telegramTag,faculty,group,email,phoneNumber,birthday,admissionDay, new UserRole(role_id,role_name)));
            }
            return users;
        }
        catch (SQLException e)
        {
            //TODO: add logging
            throw new RuntimeException(e);
        }
    }

    @Override
    public User save(User user) {
        try (PreparedStatement save = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS))
        {
            save.setString(1,user.getFirstName());
            save.setString(2,user.getLastName());
            save.setString(3,user.getMiddleName());
            save.setString(4,user.getTelegramTag());
            save.setString(5,user.getFaculty());
            save.setString(6,user.getGroup());
            save.setString(7,user.getEmail());
            save.setString(8,user.getPhoneNumber());
            save.setDate(9,java.sql.Date.valueOf(user.getBirthday()));
            save.setDate(10,java.sql.Date.valueOf(user.getAdmissionDay()));
            save.setLong(11,user.getRole().getId());
            save.executeUpdate();
            ResultSet id = save.getGeneratedKeys();
            long newId = 0;
            if (id.next()) {
                newId = id.getLong(1);
            }
            return new User(newId, user.getFirstName(),user.getLastName(),user.getMiddleName(),user.getTelegramTag(),user.getFaculty(),user.getGroup(),user.getEmail(),user.getPhoneNumber(),user.getBirthday(),user.getAdmissionDay(),user.getRole());
        }
        catch (SQLException e)
        {
            //TODO: add logging
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(User user) {
        try (PreparedStatement update = connection.prepareStatement(UPDATE_USER))
        {
            update.setString(1,user.getFirstName());
            update.setString(2,user.getLastName());
            update.setString(3,user.getMiddleName());
            update.setString(4,user.getTelegramTag());
            update.setString(5,user.getFaculty());
            update.setString(6,user.getGroup());
            update.setString(7,user.getEmail());
            update.setString(8,user.getPhoneNumber());
            update.setDate(9,java.sql.Date.valueOf(user.getBirthday()));
            update.setDate(10,java.sql.Date.valueOf(user.getAdmissionDay()));
            update.setLong(11,user.getRole().getId());
            update.setLong(11,user.getId());
            update.executeUpdate();
        }
        catch (SQLException e)
        {
            //TODO: add logging
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement delete = connection.prepareStatement(DELETE_USER))
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

    @Override
    public User getLoginInfo(String login) {
        try (PreparedStatement getLogin = connection.prepareStatement(GET_LOGIN_INFO))
        {
            getLogin.setString(1,login);
            getLogin.setString(2,login);
            ResultSet user = getLogin.executeQuery();
            if(user.next())
            {
                long id = user.getLong(1);
                String firstName = user.getString(2);
                String lastName = user.getString(3);
                String password = user.getString(4);
                return new User(id, firstName,lastName, password);
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
    public void updateLoginInfo(User user) {
        try (PreparedStatement update = connection.prepareStatement(UPDATE_LOGIN_INFO))
        {
            update.setString(1,user.getLogin());
            update.setString(2,user.getPassword());
            update.setLong(3,user.getId());
            update.executeUpdate();
        }
        catch (SQLException e)
        {
            //TODO: add logging
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<TaskUser> getUsersByTask(Task task) {
        try (PreparedStatement getUsersByTask = connection.prepareStatement(GET_USERS_BY_TASK))
        {
            getUsersByTask.setLong(1, task.getId());
            ResultSet userResult = getUsersByTask.executeQuery();
            List<TaskUser> users = new ArrayList<TaskUser>();
            while(userResult.next())
            {
                long id = userResult.getLong(1);
                String firstName = userResult.getString(2);
                String lastName = userResult.getString(3);
                String middleName = userResult.getString(4);
                String telegramTag = userResult.getString(5);
                String faculty = userResult.getString(6);
                String group = userResult.getString(7);
                String email = userResult.getString(8);
                String phoneNumber = userResult.getString(9);
                LocalDate birthday = userResult.getDate(10).toLocalDate();
                LocalDate admissionDay = userResult.getDate(11).toLocalDate();
                long role_id = userResult.getLong(12);
                String role_name = userResult.getString(13);
                long taskRoleId = userResult.getLong(14);
                String taskRoleName = userResult.getString(15);
                User user = new User(id, firstName,lastName,middleName,telegramTag,faculty,group,email,phoneNumber,birthday,admissionDay, new UserRole(role_id,role_name));
                TaskRole taskRole = new TaskRole(taskRoleId, taskRoleName);
                users.add(new TaskUser(task,user, taskRole));
            }
            return users;
        }
        catch (SQLException e)
        {
            //TODO: add logging
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getUsersByUserRole(UserRole userRole) {
        try (PreparedStatement getUsersByUserRole = connection.prepareStatement(GET_USER_BY_USER_ROLE))
        {
            getUsersByUserRole.setLong(1, userRole.getId());
            ResultSet userResult = getUsersByUserRole.executeQuery();
            List<User> users = new ArrayList<User>();
            while(userResult.next())
            {
                long id = userResult.getLong(1);
                String firstName = userResult.getString(2);
                String lastName = userResult.getString(3);
                String middleName = userResult.getString(4);
                String telegramTag = userResult.getString(5);
                String faculty = userResult.getString(6);
                String group = userResult.getString(7);
                String email = userResult.getString(8);
                String phoneNumber = userResult.getString(9);
                LocalDate birthday = userResult.getDate(10).toLocalDate();
                LocalDate admissionDay = userResult.getDate(11).toLocalDate();
                long role_id = userResult.getLong(12);
                String role_name = userResult.getString(13);
                users.add(new User(id, firstName,lastName,middleName,telegramTag,faculty,group,email,phoneNumber,birthday,admissionDay, new UserRole(role_id,role_name)));
            }
            return users;
        }
        catch (SQLException e)
        {
            //TODO: add logging
            throw new RuntimeException(e);
        }
    }
}
