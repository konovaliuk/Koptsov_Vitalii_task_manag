package com.application.dao.impl.JPA;

import com.application.dao.interfaces.UserRoleDao;
import com.application.model.Task;
import com.application.model.UserRole;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import javax.swing.text.html.parser.Entity;
import java.sql.Connection;
import java.util.List;

public class JPAUserRoleDao implements UserRoleDao {
    private EntityManager entityManager;
    public JPAUserRoleDao(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }
    @Override
    public UserRole get(long id) {
        UserRole userRole = entityManager.find(UserRole.class, id);
        return userRole;
    }

    @Override
    public List<UserRole> getAll() {
        return entityManager.createQuery("SELECT e FROM UserRole e", UserRole.class).getResultList();
    }

    @Override
    public UserRole save(UserRole userRole) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(userRole);
            transaction.commit();
        }catch ( Exception e )
        {
            transaction.rollback();
            throw e;
        }
        return userRole;
    }

    @Override
    public void update(UserRole userRole) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(userRole);
            transaction.commit();
        }catch ( Exception e )
        {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public void delete(long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entityManager.find(UserRole.class,id));
            transaction.commit();
        }catch ( Exception e )
        {
            transaction.rollback();
            throw e;
        }
    }
}
