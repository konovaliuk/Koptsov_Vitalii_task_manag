package com.application.dao.impl.JPA;

import com.application.dao.DaoFactory;
import com.application.dao.interfaces.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPADaoFactory extends DaoFactory {
    @Override
    public TaskDao getTaskDao(EntityManager entityManager) {
        return new JPATaskDao(entityManager);
    }

    @Override
    public TaskRoleDao getTaskRoleDao(EntityManager entityManager) {
        return new JPATaskRoleDao(entityManager);
    }

    @Override
    public TaskStatusDao getTaskStatusDao(EntityManager entityManager) {
        return new JPATaskStatusDao(entityManager);
    }

    @Override
    public TaskTagDao getTaskTagDao(EntityManager entityManager) {
        return new JPATaskTagDao(entityManager);
    }

    @Override
    public UserDao getUserDao(EntityManager entityManager) {
        return new JPAUserDao(entityManager);
    }

    @Override
    public UserRoleDao getUserRoleDao(EntityManager entityManager) {
        return new JPAUserRoleDao(entityManager);
    }
    
    @Override
    public TaskUserDao getTaskUserDao(EntityManager entityManager){return new JPATaskUserDao(entityManager);}
}
