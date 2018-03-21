package com.company.servlets;

import com.company.services.RegistrationService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {

    private RegistrationService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.service = (RegistrationService)config.getServletContext().getAttribute("userService");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/signUp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String checkPassword = request.getParameter("checkPassword");
        if (password.equals(checkPassword) && service.save(name, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", name);
            Cookie sessionCookie = new Cookie(name, session.getId());
            Cookie nameUser = new Cookie("name", name);
            response.addCookie(sessionCookie);
            response.addCookie(nameUser);
            response.sendRedirect("/products");
        }
        else response.sendRedirect("/signUp");
    }
}
