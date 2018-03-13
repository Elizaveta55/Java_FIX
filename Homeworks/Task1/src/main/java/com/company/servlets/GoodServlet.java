package com.company.servlets;

import com.company.models.GoodModel;
import com.company.repositories.GoodRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoodServlet extends HttpServlet{


    private GoodRepository repository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        repository = (GoodRepository)config.getServletContext().getAttribute("goodRepository");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<GoodModel> goods = repository.findAll();

        request.setAttribute("goods", goods);

        request.getRequestDispatcher("/jsp/goods.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        int amount = Integer.parseInt(request.getParameter("amount"));

        GoodModel good = GoodModel.builder()
                .name(name)
                .amount(amount)
                .build();

        repository.save(good);

        response.sendRedirect("/goods");
    }
}
