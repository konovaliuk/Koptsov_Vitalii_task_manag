package com.application.dao;

import com.application.dao.interfaces.*;
import jakarta.persistence.EntityManager;

public abstract class DaoFactory {
    public abstract TaskDao getTaskDao(EntityManager entityManager);
    public abstract TaskRoleDao getTaskRoleDao(EntityManager entityManager);
    public abstract TaskStatusDao getTaskStatusDao(EntityManager entityManager);
    public abstract TaskTagDao getTaskTagDao(EntityManager entityManager);
    public abstract UserDao getUserDao(EntityManager entityManager);
    public abstract UserRoleDao getUserRoleDao(EntityManager entityManager);
    public abstract TaskUserDao getTaskUserDao(EntityManager entityManager);
}
