package com.application.dao.impl.JPA;

import com.application.dao.interfaces.TaskTagDao;
import com.application.model.Task;
import com.application.model.TaskStatus;
import com.application.model.TaskTag;
import com.application.model.UserRole;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.sql.Connection;
import java.util.List;

public class JPATaskTagDao implements TaskTagDao {
    private EntityManager entityManager;
    public JPATaskTagDao(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }
    @Override
    public TaskTag get(long id) {
        TaskTag taskTag = entityManager.find(TaskTag.class, id);
        return taskTag;
    }

    @Override
    public List<TaskTag> getAll() {
        return entityManager.createQuery("SELECT e FROM TaskTag e", TaskTag.class).getResultList();
    }

    @Override
    public TaskTag save(TaskTag taskTag) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(taskTag);
            transaction.commit();
        }catch ( Exception e )
        {
            transaction.rollback();
            throw e;
        }
        return taskTag;
    }

    @Override
    public void update(TaskTag taskTag) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(taskTag);
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
