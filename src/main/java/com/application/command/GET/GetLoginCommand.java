package com.application.command.GET;

import com.application.command.Command;
import com.application.services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetLoginCommand implements Command {
    private final ServiceFactory serviceFactory;
    public GetLoginCommand(ServiceFactory serviceFactory)
    {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if(req.getSession().getAttribute("User") != null)
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED,"You already logged-in");
        req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
    }
}
