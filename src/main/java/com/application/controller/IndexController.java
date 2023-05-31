package com.application.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
@RestController
public class IndexController {
    private static final Logger LOGGER = LogManager.getLogger(IndexController.class);
    @RequestMapping("/")
    public String showIndex() {

        LOGGER.error("GET /index");
        return "jsp/index.jsp";
    }
}
