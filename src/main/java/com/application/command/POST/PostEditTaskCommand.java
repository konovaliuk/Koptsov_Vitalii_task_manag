package com.application.command.POST;

import com.application.command.Command;
import com.application.model.Task;
import com.application.model.TaskStatus;
import com.application.model.User;
import com.application.services.ServiceFactory;
import com.application.servlets.MainServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class PostEditTaskCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(PostEditTaskCommand.class);

    private final ServiceFactory serviceFactory;
    public PostEditTaskCommand(ServiceFactory serviceFactory)
    {
        this.serviceFactory = serviceFactory;
    }
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if(req.getSession().getAttribute("User") == null)
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        long id = Long.parseLong(req.getParameter("id"));
        Instant creationDate = Instant.parse(req.getParameter("creationDate"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Instant deadline = Instant.parse(req.getParameter("deadline"));
        int taskStatusId = Integer.parseInt(req.getParameter("taskStatusId"));
        short priority = Short.parseShort(req.getParameter("priority"));
        TaskStatus taskStatus = serviceFactory.getTaskStatusService().getTaskStatus(taskStatusId);

        Task task = new Task(id,name,description,creationDate,deadline,taskStatus,priority);
        LOGGER.error(task);
        try {
            serviceFactory.getTaskService().updateTask(task);
            LOGGER.error("task updated");
            resp.sendRedirect("/task?id="+task.getId());
        } catch (Exception e)
        {
            LOGGER.error("exceptionn");
            throw e;
        }
    }
}

