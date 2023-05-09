package com.application.dao.impl.JPA;

import com.application.dao.interfaces.TaskStatusDao;
import com.application.model.Task;
import com.application.model.TaskStatus;
import com.application.model.UserRole;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.sql.Connection;
import java.util.List;

public class JPATaskStatusDao implements TaskStatusDao {
    private EntityManager entityManager;
    public JPATaskStatusDao(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }
    @Override
    public TaskStatus get(long id) {
        TaskStatus taskStatus = entityManager.find(TaskStatus.class, id);
        return taskStatus;
    }

    @Override
    public List<TaskStatus> getAll() {
        return entityManager.createQuery("SELECT e FROM TaskStatus e", TaskStatus.class).getResultList();
    }

    @Override
    public TaskStatus save(TaskStatus taskStatus) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(taskStatus);
            transaction.commit();
        }catch ( Exception e )
        {
            transaction.rollback();
            throw e;
        }
        return taskStatus;
    }

    @Override
    public void update(TaskStatus taskStatus) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(taskStatus);
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
            entityManager.remove(entityManager.find(TaskStatus.class,id));
            transaction.commit();
        }catch ( Exception e )
        {
            transaction.rollback();
            throw e;
        }
    }
}
