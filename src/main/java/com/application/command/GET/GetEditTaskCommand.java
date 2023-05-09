package com.application.command.GET;

import com.application.command.Command;
import com.application.model.Task;
import com.application.model.TaskStatus;
import com.application.services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetEditTaskCommand implements Command {
    private final ServiceFactory serviceFactory;
    public GetEditTaskCommand(ServiceFactory serviceFactory)
    {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if(req.getSession().getAttribute("User") == null)
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        long id = 0;
        try{
            id = Long.parseLong(req.getParameter("id"));
        }catch (Exception e)
        {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
        Task task = serviceFactory.getTaskService().getTask(id);
        List<TaskStatus> taskStatusList = serviceFactory.getTaskStatusService().getAllTaskStatuses();
        req.setAttribute("task", task);
        req.setAttribute("taskStatusList", taskStatusList);
        req.getRequestDispatcher("jsp/task/task_edit.jsp").forward(req, resp);
    }
}
