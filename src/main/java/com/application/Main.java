package com.application;

import com.application.dao.ConnectionPool;
import com.application.dao.DaoFactory;
import com.application.dao.impl.JPA.JPADaoFactory;
import com.application.dao.impl.JPA.JPATaskDao;
import com.application.dao.impl.JPA.JPAUserDao;
import com.application.dao.interfaces.*;
import com.application.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("TaskManager").createEntityManager();
        DaoFactory daoFactory = new JPADaoFactory();
        TaskDao taskDao = daoFactory.getTaskDao(entityManager);
        System.out.println(taskDao.get(1));

        TaskStatus taskStatus = entityManager.find(TaskStatus.class,1);
        Task task = new Task(0L,"Say hi","Say hi to somebody", Timestamp.from(Instant.now()),Timestamp.from(Instant.now()),taskStatus, (short) 0,new ArrayList<TaskUser>(),new ArrayList<TaskTag>());
        System.out.println(task);
        Task newTask = taskDao.save(task);
        System.out.println(newTask);

        UserDao userDao = new JPAUserDao(entityManager);
        User user = userDao.getAll().get(0);
        System.out.println(user);
        TaskRoleDao taskRoleDao = daoFactory.getTaskRoleDao(entityManager);
        TaskRole taskRole = taskRoleDao.get(1);
        System.out.println(taskRole);
        TaskUserId taskUserId = new TaskUserId(user.getId(),newTask.getId());

        TaskUser taskUser = new TaskUser();
        taskUser.setId(taskUserId);
        taskUser.setTaskRole(taskRole);
        taskUser.setUser(user);
        taskUser.setTask(newTask);
        TaskUserDao taskUserDao = daoFactory.getTaskUserDao(entityManager);
        taskUserDao.save(taskUser);

        EntityManager entityManager2 = Persistence.createEntityManagerFactory("TaskManager").createEntityManager();
        TaskDao taskDao2 = daoFactory.getTaskDao(entityManager2);
        newTask = taskDao2.get(newTask.getId());
        List<TaskUser> testTaskUser = newTask.getTaskUsers();
        System.out.println(testTaskUser);

        TaskTagDao taskTagDao = daoFactory.getTaskTagDao(entityManager2);
        TaskTag tag1 = taskTagDao.get(0);
        TaskTag tag2 = taskTagDao.get(1);
        newTask.getTags().add(tag1);
        newTask.getTags().add(tag2);
        taskDao.update(newTask);
        taskUserDao.delete(taskUser);
        taskDao.delete(newTask.getId());
    }
}
