package com.application.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ConnectionPool {
    private List<Connection> avaliable;
    private List<Connection> inUse;
    private final int poolSize;
    private static ConnectionPool instance;
    private ConnectionPool(int size)
    {
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
                //TODO: add loging
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
                //TODO: add logging
                throw new RuntimeException(e);
            }
            return conn;
        }
    }
}
