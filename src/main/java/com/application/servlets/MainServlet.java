package com.application.servlets;

import com.application.command.Command;
import com.application.command.GET.*;
import com.application.command.POST.*;
import com.application.dao.DaoFactory;
import com.application.dao.impl.JDBC.JDBCDaoFactory;
import com.application.services.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(urlPatterns = {"/"})
public class MainServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(MainServlet.class);
    private static final DaoFactory daoFactory = new JDBCDaoFactory();
    private HashMap<String, Command> getCommands;
    private HashMap<String, Command> postCommands;

    @Override
    public void init()
    {
        getCommands = new HashMap<>();
        postCommands = new HashMap<>();
        ServiceFactory serviceFactory = new ServiceFactory(daoFactory);
        getCommands.put("/login",new GetLoginCommand(serviceFactory));
        getCommands.put("/",new GetIndexCommand(serviceFactory));
        getCommands.put("/registration",new GetRegisterCommand(serviceFactory));
        getCommands.put("/tasks",new GetTasksCommand(serviceFactory));
        getCommands.put("/task",new GetOneTaskCommand(serviceFactory));
        postCommands.put("/login", new PostLoginCommand(serviceFactory));
        postCommands.put("/registration", new PostRegisterCommand(serviceFactory));
        getCommands.put("/logout", new GetLogoutCommand(serviceFactory));
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.error("GET " + req.getRequestURL());
        Command command = getCommands.get(req.getRequestURI());
        if(command != null)
        {
            command.execute(req,resp);
        }else
        {
            try {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            } catch (IOException e) {
                LOGGER.error("Error in servlet: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.error("POST " + req.getRequestURL());
        Command command = postCommands.get(req.getRequestURI());
        if(command != null)
        {
            try {
                command.execute(req,resp);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }else
        {
            try {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            } catch (IOException e) {
                LOGGER.error("Error in servlet: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
}
