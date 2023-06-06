package com.application.controller;

import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
@Controller
public class IndexController {
    private static final Logger LOGGER = LogManager.getLogger(IndexController.class);
    @RequestMapping("/")
    public ModelAndView showIndex() {
        LOGGER.error("GET /index");
        String s;
        return new ModelAndView("index");
    }
}
