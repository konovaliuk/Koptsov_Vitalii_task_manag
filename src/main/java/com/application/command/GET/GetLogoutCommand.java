package com.application.command.GET;

import com.application.command.Command;
import com.application.services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetLogoutCommand implements Command {
    private final ServiceFactory serviceFactory;
    public GetLogoutCommand(ServiceFactory serviceFactory)
    {
        this.serviceFactory = serviceFactory;
    }
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if(req.getSession().getAttribute("User") == null)
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        req.getSession().invalidate();
        req.getRequestDispatcher("jsp/index.jsp").forward(req,resp);
    }
}
