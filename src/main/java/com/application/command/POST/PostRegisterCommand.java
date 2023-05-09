package com.application.command.POST;

import com.application.command.Command;
import com.application.command.GET.GetRegisterCommand;
import com.application.model.User;
import com.application.model.UserRole;
import com.application.services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PostRegisterCommand implements Command {
    private final ServiceFactory serviceFactory;
    public PostRegisterCommand(ServiceFactory serviceFactory)
    {
        this.serviceFactory = serviceFactory;
    }
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if(req.getSession().getAttribute("User") != null)
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED,"You already logged-in");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String passwordConfirmation = req.getParameter("passwordConfirmation");
        if(!passwordConfirmation.contentEquals(password)) {
            ErrorShow(req,resp, "Passwords must be equal");
        }
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String middleName = req.getParameter("middleName");
        String telegramTag = req.getParameter("telegramTag");
        if(!telegramTag.startsWith("@")) {
            ErrorShow(req,resp, "Telegram tag must start with @");
        }
        String faculty = req.getParameter("faculty");
        String group = req.getParameter("group");
        if(!group.contains("-")) {
            ErrorShow(req,resp, "Error in faculty field");
        }
        String email = req.getParameter("email");
        if(!email.matches("^[^@]{1,}@[^@]{1,}")) {
            ErrorShow(req,resp, "Error in email");
        }
        String phoneNumber = req.getParameter("phoneNumber");
        if(!phoneNumber.matches("^\\d{10}$")) {
            ErrorShow(req,resp, "Phone number in format xxxxxxxxxx");
        }
        String birthdayFieldValue = req.getParameter("birthday");
        LocalDate birthday = LocalDate.parse(birthdayFieldValue, DateTimeFormatter.ISO_LOCAL_DATE);
        if(birthday.isAfter(LocalDate.now())) {
            ErrorShow(req,resp, "Birthday must can`t be after today");
        }
        String admissionDayFieldValue = req.getParameter("admissionDay");
        LocalDate admissionDay = LocalDate.parse(admissionDayFieldValue, DateTimeFormatter.ISO_LOCAL_DATE);
        if(admissionDay.isAfter(LocalDate.now())) {
            ErrorShow(req,resp, "Admission day must can`t be after today");
        }
        long userRoleId = Long.parseLong(req.getParameter("roleId"));
        User newUser = new User(-1,
                                login,
                                password,
                                firstName,
                lastName,
                middleName,
                telegramTag,
                faculty,
                group,
                email,
                phoneNumber,
                birthday,
                admissionDay,
                new UserRole(userRoleId, ""));
        try {
            User user = serviceFactory.getUserService().regiserUser(newUser);
            //TODO: think about permissions
            req.getSession().setAttribute("User",user);
            resp.sendRedirect("/");
        } catch (SQLException e) {
            //find duplicate entry exception
            ErrorShow(req,resp, "Database error please try later");
        }
    }

    private void ErrorShow(HttpServletRequest req, HttpServletResponse resp, String message)throws IOException, ServletException
    {
        req.setAttribute("error", message);
        new GetRegisterCommand(serviceFactory).execute(req,resp);
    }
}
