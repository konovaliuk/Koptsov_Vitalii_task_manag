package com.application;

import com.application.dao.impl.JPA.JPADaoFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class Config {
    @Bean
    public DaoFactory daoFactory() {
        return new JPADaoFactory();
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver irvr = new InternalResourceViewResolver();
        irvr.setPrefix("templates/");
        irvr.setSuffix(".html");
        return irvr;
    }
}
