package com.application.dao.impl.JPA;

import com.application.dao.interfaces.UserDao;
import com.application.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.sql.Connection;
import java.util.List;

public class JPAUserDao implements UserDao {
    private EntityManager entityManager;
    public JPAUserDao(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }
    @Override
    public User get(long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public List<User> getAll() {
        return entityManager.createQuery("SELECT e FROM User e", User.class).getResultList();
    }

    @Override
    public User save(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
        }catch ( Exception e )
        {
            transaction.rollback();
            throw e;
        }
        return user;
    }

    @Override
    public void update(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(user);
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
            entityManager.remove(entityManager.find(User.class,id));
            transaction.commit();
        }catch ( Exception e )
        {
            transaction.rollback();
            throw e;
        }
    }
    public User getUserByLoginOrEmail(String login)
    {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE U.login = :login OR u.email = :email", User.class);
        query.setParameter(1, login);
        query.setParameter(2, login);
        return query.getSingleResult();
    }
}
