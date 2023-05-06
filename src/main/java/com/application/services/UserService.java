package com.application.services;

import com.application.dao.DaoFactory;
import com.application.dao.ConnectionPool;
import com.application.model.Task;
import com.application.model.TaskUser;
import com.application.model.User;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import com.application.model.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.security.auth.login.LoginException;

public class UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);
    private final DaoFactory daoFactory;
    public UserService(DaoFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }
    public User getUser(long id) {
        
        Connection con = ConnectionPool.getInstance().getConnection();
        try{            
            return daoFactory.getUserDao().get(con, id);
        }
        catch (Exception e){
            LOGGER.error("Error: " + e.getMessage());
            throw new RuntimeException("Error while getting user");
        }
    }
    public List<User> getAllUsers(){
        
        Connection con = ConnectionPool.getInstance().getConnection();
        try{            
            return daoFactory.getUserDao().getAll(con);
        }
        catch (Exception e){
            LOGGER.error("Error : " + e.getMessage());
            throw new RuntimeException("Error while getting all users");
        }
    }
    public void updateUser(User user){
        Connection con = ConnectionPool.getInstance().getConnection();
        try{            
            try {
                con.setAutoCommit(false);
                daoFactory.getUserDao().update(con, user);
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
            throw new RuntimeException("Error while update user");
        }
        finally {
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }
    public User regiserUser(User user) throws SQLException {
        Connection con = ConnectionPool.getInstance().getConnection();
        try
        {
            try {
                con.setAutoCommit(false);
                byte[] salt = newSalt();
                byte[] hashedPassword = getPasswordHash(user.getPassword(), salt);
                UserPassword userPassword = new UserPassword(hashedPassword, salt);
                user.setPassword(userPassword.toString());
                User newUser = daoFactory.getUserDao().save(con, user);
                con.commit();
                return newUser;
            } catch (Exception e) {
                try {
                    con.rollback();
                } catch (Exception ex){
                    LOGGER.error("Error while rollback : " + e.getMessage());
                }
                throw new RuntimeException("Error while user registration" + e.getMessage());
            } finally {
                con.setAutoCommit(true);
            }
        } catch (Exception e){
            LOGGER.error("Error: " + e.getMessage());
            throw e;
        }
        finally {
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }
    public User loginUser(String login, String password) throws LoginException, SQLException {
        
        Connection con = ConnectionPool.getInstance().getConnection();
        try{            
            User usersWithLogin = daoFactory.getUserDao().getUserByLoginOrEmail(con, login);
            if(usersWithLogin == null)
                throw new LoginException("No user with such login or email");
            UserPassword userPassword = new UserPassword(usersWithLogin.getPassword());
            byte[] hashedInputPassword = getPasswordHash(password, userPassword.salt);
            boolean passwordsEquals = Arrays.equals(userPassword.hashedPassword, hashedInputPassword);
            if(passwordsEquals)
                return usersWithLogin;
            else
                throw new LoginException("Password not match");
        }
        catch (Exception e){
            LOGGER.error("Error : " + e.getMessage());
            throw e;
        }
        finally {
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }
    //TODO: delete user
    public List<User> getAllUsersAssignedOnTask(Task task)
    {
        
        Connection con = ConnectionPool.getInstance().getConnection();
        try{            
            List<TaskUser> userTasksWithRole = daoFactory.getUserDao().getUsersByTask(con, task);
            List<User> taskUsers = userTasksWithRole.stream().map((x) -> x.getUser()).collect(Collectors.toList());
            return taskUsers;
        }
        catch (Exception e){
            LOGGER.error("Error : " + e.getMessage());
            throw new RuntimeException("Error while getting all users");
        }
        finally {
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }
    public List<User> getAllUsersWithRole(UserRole role)
    {
        
        Connection con = ConnectionPool.getInstance().getConnection();
        try{            
            List<User> usersWithRole = daoFactory.getUserDao().getUsersByUserRole(con, role);
            return usersWithRole;
        }
        catch (Exception e){
            LOGGER.error("Error : " + e.getMessage());
            throw new RuntimeException("Error while getting all users");
        }
        finally {
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }
    private byte[] getPasswordHash(String password, byte[] salt)
    {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return hash;
        } catch (NoSuchAlgorithmException e)
        {
            LOGGER.error("No such algorithm in SecretKeyFactory. " + e.getMessage());
        } catch (InvalidKeySpecException e)
        {
            LOGGER.error("Error: " + e.getMessage());
        }
        throw new RuntimeException("Error while hashing password");
    }
    private byte[] newSalt()
    {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
    public class UserPassword {
        private final byte[] hashedPassword;
        private final byte[] salt;
        public UserPassword(byte[] hashedPassword, byte[]salt)
        {
            this.hashedPassword = hashedPassword;
            this.salt = salt;
        }
        public UserPassword(String hashedPasswordWithSalt)
        {
            byte[] saltAndPassword = Base64.getDecoder().decode(hashedPasswordWithSalt);
            this.salt = Arrays.copyOfRange(saltAndPassword,0,16);
            this.hashedPassword = Arrays.copyOfRange(saltAndPassword, 16, saltAndPassword.length);
        }

        @Override
        public String toString() {

            byte[] saltAndPassword = new byte[salt.length+hashedPassword.length];
            System.arraycopy(salt,0,saltAndPassword,0, salt.length);
            System.arraycopy(hashedPassword,0,saltAndPassword,salt.length, hashedPassword.length);
            return Base64.getEncoder().encodeToString(saltAndPassword);
        }

        public byte[] hashedPassword() {
            return hashedPassword;
        }

        public byte[] salt() {
            return salt;
        }
    }
}
