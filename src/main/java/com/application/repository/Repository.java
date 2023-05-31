package com.application.repository;

import java.util.List;

public interface Repository<T, ID> {
    T findOne(ID id);
    List<T> findAll();
    T save(T entity);
    void change(T entity);
    void delete(ID id);
}
