package com.application.command.GET;

import com.application.command.Command;
import com.application.services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetIndexCommand implements Command {
    private final ServiceFactory serviceFactory;
    public GetIndexCommand(ServiceFactory serviceFactory)
    {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        req.getRequestDispatcher("jsp/index.jsp").forward(req, resp);
    }
}
