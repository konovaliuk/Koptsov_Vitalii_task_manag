package com.application.dao.interfaces;

import java.util.List;
public interface Dao<T> {
    T get(long id);
    List<T> getAll();
    T save(T t);

    void update(T t);

    void delete(long id);
}
