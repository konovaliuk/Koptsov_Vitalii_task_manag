package com.application;

import com.application.dao.impl.JPA.JPAUserRoleDao;
import com.application.dao.interfaces.UserRoleDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Application {
    public static void main(String[] args) {
        //TODO: Think about this
        SpringApplication.run(Application.class, args);
    }
}
