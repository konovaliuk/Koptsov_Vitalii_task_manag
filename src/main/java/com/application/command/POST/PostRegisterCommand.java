package com.application.command.POST;

import com.application.command.Command;
import com.application.model.User;
import com.application.model.UserRole;
import com.application.services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String passwordConfirmation = req.getParameter("passwordConfirmation");
        if(!passwordConfirmation.contentEquals(password)) {
            req.setAttribute("passwordError", "Passwords must be equal");
            //TODO: error on frontend
        }
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String middleName = req.getParameter("middleName");
        String telegramTag = req.getParameter("telegramTag");
        String faculty = req.getParameter("faculty");
        String group = req.getParameter("group");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phoneNumber");
        String birthdayFieldValue = req.getParameter("birthday");
        LocalDate birthday = LocalDate.parse(birthdayFieldValue, DateTimeFormatter.ISO_LOCAL_DATE);
        String admissionDayFieldValue = req.getParameter("admissionDay");
        LocalDate admissionDay = LocalDate.parse(admissionDayFieldValue, DateTimeFormatter.ISO_LOCAL_DATE);
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
            req.setAttribute("error","Database error please try later");
            req.getRequestDispatcher("jsp/registration.jsp").forward(req,resp);
        }
    }
}
