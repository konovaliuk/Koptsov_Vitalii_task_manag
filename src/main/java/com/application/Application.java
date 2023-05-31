package com.application;

import com.application.dao.ConnectionPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ComponentScan(basePackages = {"com.application.dao"})
public class Application {
    public static void main(String[] args) {
        //TODO: Think about this
        SpringApplication.run(Application.class, args);
    }
}
