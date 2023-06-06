package com.application.repository;

import com.application.dao.interfaces.UserDao;
import com.application.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import com.application.DaoFactory;

import java.util.List;

@org.springframework.stereotype.Repository
public class UserRepository implements Repository<User,Long>{
    private EntityManager entityManager = Persistence.createEntityManagerFactory("TaskManager").createEntityManager();
    private UserDao userDao;
    @Autowired
    public UserRepository(DaoFactory daoFactory)
    {
        userDao = daoFactory.getUserDao(entityManager);
    }
    @Override
    public User findOne(Long l) {
        return userDao.get(l);
    }
    public User findByEmailOrLogin(String login)
    {
        return userDao.getUserByLoginOrEmail(login);
    }
    @Override
    public List<User> findAll() {
        return userDao.getAll();
    }

    @Override
    public User save(User entity) {
        User user = userDao.save(entity);
        return user;
    }

    @Override
    public void change(User entity) {
        userDao.update(entity);
    }

    @Override
    public void delete(Long l) {
        userDao.delete(l);
    }
}
