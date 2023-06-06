package com.application.repository;

import com.application.dao.interfaces.TaskStatusDao;
import com.application.model.TaskStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import com.application.DaoFactory;

import java.util.List;

@org.springframework.stereotype.Repository
public class TaskStatusRepository implements Repository<TaskStatus,Long>{
    private EntityManager entityManager = Persistence.createEntityManagerFactory("TaskManager").createEntityManager();
    private TaskStatusDao taskStatusDao;
    @Autowired
    public TaskStatusRepository(DaoFactory daoFactory)
    {
        taskStatusDao = daoFactory.getTaskStatusDao(entityManager);
    }
    @Override
    public TaskStatus findOne(Long l) {
        return taskStatusDao.get(l);
    }

    @Override
    public List<TaskStatus> findAll() {
        return taskStatusDao.getAll();
    }

    @Override
    public TaskStatus save(TaskStatus entity) {
        TaskStatus task = taskStatusDao.save(entity);
        return task;
    }

    @Override
    public void change(TaskStatus entity) {
        taskStatusDao.update(entity);
    }

    @Override
    public void delete(Long l) {
        taskStatusDao.delete(l);
    }
}
