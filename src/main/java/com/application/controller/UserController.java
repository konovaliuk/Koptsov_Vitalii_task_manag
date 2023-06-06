package com.application.controller;

import com.application.model.User;
import com.application.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private static final Logger LOGGER = LogManager.getLogger(TaskController.class);
    @Autowired
    UserService userService;
    @GetMapping(value = "")
    public String showUserById(@RequestParam("id") int id, HttpSession session, Model model)
    {
        LOGGER.error("GET /user/" + id);
        if(session.getAttribute("user") == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        User user = userService.getUser(id);
        if(user == null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        model.addAttribute("user", user);
        return "user/user";
    }

    @GetMapping(value = "all")
    public String showUsers(HttpSession session, Model model)
    {
        LOGGER.error("GET /user/all");
        if(session.getAttribute("user") == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        List<User> userList = userService.getAllUsers();
        model.addAttribute("userList", userList);
        return "user/users";
    }
}
