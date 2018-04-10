package com.company.servlets;

import com.company.models.Good;
import com.company.services.GoodService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class GoodServlet extends HttpServlet {


    private GoodService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.service = (GoodService) config.getServletContext().getAttribute("goodService");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Good> goods = service.showAll();
        request.setAttribute("goods", goods);

        request.getRequestDispatcher("/jsp/goods.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        int amount = Integer.parseInt(request.getParameter("amount"));

        Good good = Good.builder()
                .name(name)
                .amount(amount)
                .build();

        service.workWithGood(good);

        response.sendRedirect("/products");
    }
}
