package com.company.servlets;

import com.company.services.LoginService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet{

    private LoginService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.service = (LoginService)config.getServletContext().getAttribute("LoginService");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        if (service.check(name, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", name);
            Cookie nameUser = new Cookie("name", name);
            response.addCookie(nameUser);
            response.sendRedirect(request.getContextPath() + "/products");
        }
        else response.sendRedirect("/login");
    }

}
