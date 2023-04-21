package com.application.dao.interfaces;

import java.sql.Connection;
import java.util.List;
public interface Dao<T> {
    T get(Connection connection, long id);
    List<T> getAll(Connection connection);
    T save(Connection connection, T t);
    void update(Connection connection, T t);
    void delete(Connection connection, long id);
}
