package com.application.command.GET;

import com.application.command.Command;
import com.application.model.UserRole;
import com.application.services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetRegisterCommand implements Command {
    private final ServiceFactory serviceFactory;
    public GetRegisterCommand(ServiceFactory serviceFactory)
    {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if(req.getSession().getAttribute("User") != null)
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED,"You already logged-in");
        List<UserRole> userRoles = serviceFactory.getUserRoleService().getAllUserRoles();
        req.setAttribute("roleList", userRoles);
        req.getRequestDispatcher("jsp/registration.jsp").forward(req, resp);
    }
}
