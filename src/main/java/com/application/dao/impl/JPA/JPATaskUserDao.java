package com.application.dao.impl.JPA;

import com.application.dao.interfaces.TaskUserDao;
import com.application.model.Task;
import com.application.model.TaskUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class JPATaskUserDao implements TaskUserDao {
    private EntityManager entityManager;
    public JPATaskUserDao(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    public void save(TaskUser taskUser) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(taskUser);
            transaction.commit();
        }catch ( Exception e )
        {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public void updateTaskRole(TaskUser taskUser) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(taskUser);
            transaction.commit();
        }catch ( Exception e )
        {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public void delete(TaskUser taskUser) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(taskUser);
            transaction.commit();
        }catch ( Exception e )
        {
            transaction.rollback();
            throw e;
        }
    }
}
