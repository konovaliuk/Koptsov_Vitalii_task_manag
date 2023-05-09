package com.application.dao.impl.JPA;

import com.application.dao.interfaces.TaskDao;
import com.application.model.Task;
import com.application.model.TaskUser;
import com.application.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.transaction.Transaction;

import java.util.List;

public class JPATaskDao implements TaskDao {
    private EntityManager entityManager;
    public JPATaskDao(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }
    @Override
    public Task get(long id) {
        Task task = entityManager.find(Task.class, id);
        return task;
    }

    @Override
    public List<Task> getAll() {
        return entityManager.createQuery("SELECT e FROM Task e", Task.class).getResultList();
    }

    @Override
    public Task save(Task task) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(task);
            transaction.commit();
        }catch ( Exception e )
        {
            transaction.rollback();
            throw e;
        }
        return task;
    }

    @Override
    public void update(Task task) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(task);
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
            entityManager.remove(entityManager.find(Task.class,id));
            transaction.commit();
        }catch ( Exception e )
        {
            transaction.rollback();
            throw e;
        }
    }
}
