package com.application.command.GET;

import com.application.command.Command;
import com.application.model.Task;
import com.application.services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetTasksCommand implements Command {
    private final ServiceFactory serviceFactory;
    public GetTasksCommand(ServiceFactory serviceFactory)
    {
        this.serviceFactory = serviceFactory;
    }
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (req.getSession().getAttribute("User") == null)
        {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            throw new ServletException();
        }
        List<Task> taskList = serviceFactory.getTaskService().getAllTasks();
        req.setAttribute("taskList", taskList);
        req.getRequestDispatcher("jsp/tasks.jsp").forward(req, resp);
    }
}
