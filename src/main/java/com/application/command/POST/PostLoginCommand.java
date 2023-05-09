package com.application.command.POST;

import com.application.command.Command;
import com.application.model.User;
import com.application.services.ServiceFactory;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class PostLoginCommand implements Command {
    private final ServiceFactory serviceFactory;
    public PostLoginCommand(ServiceFactory serviceFactory)
    {
        this.serviceFactory = serviceFactory;
    }
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if(req.getSession().getAttribute("User") != null)
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED,"You already logged-in");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            User user = serviceFactory.getUserService().loginUser(login, password);
            //TODO: think about permissions
            req.getSession().setAttribute("User",user);
            resp.sendRedirect("/");
        } catch (SQLException e) {
            req.setAttribute("error","Database error please try later");
            req.getRequestDispatcher("jsp/login.jsp").forward(req,resp);
        } catch (LoginException e) {
            req.setAttribute("error","Problem with login or email");
            req.getRequestDispatcher("jsp/login.jsp").forward(req,resp);
        }
    }
}
