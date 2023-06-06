package com.application.controller;

import com.application.model.User;
import com.application.model.UserRole;
import com.application.services.UserRoleService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.List;

@Controller
@AllArgsConstructor
public class AuthorizationController {
    private static final Logger LOGGER = LogManager.getLogger(AuthorizationController.class);
    @Autowired
    private final UserService userService;
    @Autowired
    private final UserRoleService userRoleService;
    @GetMapping(value = "/login")
    public String showLoginPage(HttpSession session, Model model)
    {
        LOGGER.error("GET /login");
        if(session.getAttribute("user") != null)
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED,"You already logged-in");
        return "login";
    }
    @GetMapping(value = "/register")
    public String showRegisterPage(HttpSession session, Model model)
    {
        LOGGER.error("GET /register");
        if(session.getAttribute("user") != null)
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED,"You already logged-in");
        List<UserRole> userRoles = userRoleService.getAllUserRole();
        model.addAttribute("roleList", userRoles);
        return "registration";
    }
    @GetMapping(value = "/logout")
    public String logout(HttpSession session)
    {
        LOGGER.error("GET /logout");
        if(session.getAttribute("user") == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"You already logged-in");
        session.invalidate();
        return "index";
    }

    @PostMapping(value = "/login", consumes = {"*/*"})
    public ModelAndView login(HttpSession session, @RequestParam("password") String password, @RequestParam("login") String login, Model model)
    {
        LOGGER.error("POST /login");
        if(session.getAttribute("user") != null)
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED,"You already logged-in");
        try {
            User user = userService.loginUser(login, password);
            //TODO: think about permissions
            session.setAttribute("user",user);
            return new ModelAndView("redirect:/");
        } catch (LoginException e) {
            model.addAttribute("errorMessage","Problem with login or email");
            return new ModelAndView(showLoginPage(session, model));
        }
    }
    @PostMapping(value = "/register")
    public ModelAndView register(HttpSession session, Model model,
                                 @RequestParam("login") String login,
                                 @RequestParam("password") String password,
                                 @RequestParam("passwordConfirmation") String passwordConfirmation,
                                 @RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName,
                                 @RequestParam("middleName") String middleName,
                                 @RequestParam("telegramTag") String telegramTag,
                                 @RequestParam("faculty") String faculty,
                                 @RequestParam("group") String group,
                                 @RequestParam("email") String email,
                                 @RequestParam("phoneNumber") String phoneNumber,
                                 @RequestParam("birthday") String birthdayFieldValue,
                                 @RequestParam("admissionDay") String admissionDayFieldValue,
                                 @RequestParam("roleId") Long userRoleId)
    {
        LOGGER.error("POST /register");

        if(session.getAttribute("user") != null)
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED,"You already logged-in");

        if(!passwordConfirmation.contentEquals(password)) {
            model.addAttribute("errorMessage", "Passwords must be equal");
            return new ModelAndView(showRegisterPage(session, model));
        }

        if(!telegramTag.startsWith("@")) {
            model.addAttribute("errorMessage", "Telegram tag must start with @");
            return new ModelAndView(showRegisterPage(session, model));
        }

        if(!group.contains("-")) {
            model.addAttribute("errorMessage", "Error in faculty field");
            return new ModelAndView(showRegisterPage(session, model));
        }

        if(!email.matches("^[^@]{1,}@[^@]{1,}")) {
            model.addAttribute("errorMessage", "Error in email");
            return new ModelAndView(showRegisterPage(session, model));
        }

        if(!phoneNumber.matches("^\\d{10}$")) {
            model.addAttribute("errorMessage", "Phone number in format xxxxxxxxxx");
            return new ModelAndView(showRegisterPage(session, model));
        }


        java.util.Date birthdayUtilDate = new java.util.Date();
        java.util.Date admissionUtilDate = new java.util.Date();;
        try {
            birthdayUtilDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthdayFieldValue);
            admissionUtilDate = new SimpleDateFormat("yyyy-MM-dd").parse(admissionDayFieldValue);
        }catch (ParseException e)
        {
            model.addAttribute("errorMessage", "Can`t parse date");
            return new ModelAndView(showRegisterPage(session, model));
        }

        if(birthdayUtilDate.after(new java.util.Date())) {
            model.addAttribute("errorMessage", "Birthday must can`t be after today");
            return new ModelAndView(showRegisterPage(session, model));
        }

        if(admissionUtilDate.after(new java.util.Date())) {
            model.addAttribute("errorMessage", "Admission day must can`t be after today");
            return new ModelAndView(showRegisterPage(session, model));
        }
        Date birthday = new Date(birthdayUtilDate.getTime());
        Date admissionDay = new Date(admissionUtilDate.getTime());

        User user = new User();
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setMiddleName(middleName);
        user.setBirthday(birthday);
        user.setAdmissionDay(admissionDay);
        user.setEmail(email);
        user.setTelegramTag(telegramTag);
        user.setPhoneNumber(phoneNumber);
        user.setLogin(login);
        user.setFaculty(faculty);
        user.setGroup(group);
        try {
            User newUser = userService.regiserUser(user, userRoleId);
            //TODO: think about permissions
            session.setAttribute("user",user);
            return new ModelAndView("redirect:/");
        } catch (SQLException e) {
            //find duplicate entry exception
            model.addAttribute("errorMessage", "Database error please try later");
            return new ModelAndView(showRegisterPage(session, model));
        }
    }
}
