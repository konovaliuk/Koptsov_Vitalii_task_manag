package com.application.dao.impl.JPA;

import com.application.dao.interfaces.TaskRoleDao;
import com.application.model.Task;
import com.application.model.TaskRole;
import com.application.model.UserRole;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.sql.Connection;
import java.util.List;

public class JPATaskRoleDao implements TaskRoleDao {

    private EntityManager entityManager;
    public JPATaskRoleDao(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }
    @Override
    public TaskRole get(long id) {
        TaskRole taskRole = entityManager.find(TaskRole.class, id);
        return taskRole;
    }

    @Override
    public List<TaskRole> getAll() {
        return entityManager.createQuery("SELECT e FROM TaskRole e", TaskRole.class).getResultList();
    }

    @Override
    public TaskRole save(TaskRole taskRole) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(taskRole);
            transaction.commit();
        }catch ( Exception e )
        {
            transaction.rollback();
            throw e;
        }
        return taskRole;
    }

    @Override
    public void update(TaskRole taskRole) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(taskRole);
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
            entityManager.remove(entityManager.find(TaskRole.class,id));
            transaction.commit();
        }catch ( Exception e )
        {
            transaction.rollback();
            throw e;
        }
    }
}
