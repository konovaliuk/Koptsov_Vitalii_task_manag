package com.application.repository;

import com.application.dao.interfaces.UserRoleDao;
import com.application.model.UserRole;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import com.application.DaoFactory;

import java.util.List;

@org.springframework.stereotype.Repository
public class UserRoleRepository implements Repository<UserRole,Long> {
    private EntityManager entityManager = Persistence.createEntityManagerFactory("TaskManager").createEntityManager();
    private UserRoleDao userRoleDao;
    @Autowired
    public UserRoleRepository(DaoFactory daoFactory) {
        userRoleDao = daoFactory.getUserRoleDao(entityManager);
    }
    @Override
    public UserRole findOne(Long l) {
        return userRoleDao.get(l);
    }
    @Override
    public List<UserRole> findAll() {
        return userRoleDao.getAll();
    }

    @Override
    public UserRole save(UserRole entity) {
        UserRole userRole = userRoleDao.save(entity);
        return userRole;
    }

    @Override
    public void change(UserRole entity) {
        userRoleDao.update(entity);
    }

    @Override
    public void delete(Long l) {
        userRoleDao.delete(l);
    }
}
