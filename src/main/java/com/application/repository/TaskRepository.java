package com.application.repository;

import com.application.dao.DaoFactory;
import com.application.dao.interfaces.TaskDao;
import com.application.model.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Repository
public class TaskRepository implements Repository<Task,Long>{
    private EntityManager entityManager = Persistence.createEntityManagerFactory("TaskManager").createEntityManager();
    private TaskDao taskDao;
    @Autowired
    public TaskRepository(DaoFactory daoFactory)
    {
        taskDao = daoFactory.getTaskDao(entityManager);
    }
    @Override
    public Task findOne(Long l) {
        return taskDao.get(l);
    }

    @Override
    public List<Task> findAll() {
        return taskDao.getAll();
    }

    @Override
    public Task save(Task entity) {
        Task task = taskDao.save(entity);
        return task;
    }

    @Override
    public void change(Task entity) {
        taskDao.update(entity);
    }

    @Override
    public void delete(Long l) {
        taskDao.delete(l);
    }
}
