package com.application.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private List<Connection> avaliable;
    private List<Connection> inUse;
    private final int poolSize;
    private static ConnectionPool instance;
    private ConnectionPool(int size)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        ConnectionGenerator generator = new ConnectionGenerator();
        poolSize = size;
        avaliable = new ArrayList<>();
        inUse = new ArrayList<>();
        for (int i = 0; i < poolSize; i++) {
            avaliable.add(generator.getConnection());
        }
    }

    public static ConnectionPool getInstance() {
        if(instance == null)
        {
            instance = new ConnectionPool(5);
        }
        return instance;
    }

    public Connection getConnection()
    {
        Connection connection = avaliable.remove(avaliable.size() - 1);
        inUse.add(connection);
        return connection;
    }

    public void releaseConnection(Connection connection)
    {
        avaliable.add(connection);
        inUse.remove(connection);
    }

    public void shutdown()
    {

        for (Connection conn : new ArrayList<Connection>(inUse)) {
            releaseConnection(conn);
        }
        for (Connection conn : avaliable) {
            try {
                conn.close();
            }
            catch (SQLException e)
            {
                LOGGER.error("Error while freeing connection : {}", e.getMessage());
            }
        }
        avaliable.clear();
    }

    class ConnectionGenerator {
        String url;
        String user;
        String password;
        public ConnectionGenerator()
        {
            ResourceBundle rb = ResourceBundle.getBundle("database");
            url = rb.getString("url");
            user = rb.getString("user");
            password = rb.getString("password");
        }

        public Connection getConnection(){
            Connection conn;
            try {
                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException e)
            {
                LOGGER.error("Error while creation connection : {}", e.getMessage());
                throw new RuntimeException(e);
            }
            return conn;
        }
    }
}
