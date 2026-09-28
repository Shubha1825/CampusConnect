package com.campusconnect.controller;

import java.io.IOException;
import java.util.List;

import com.campusconnect.dao.ItemDAO;
import com.campusconnect.model.Item;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/items")
public class ItemListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ItemDAO itemDAO = new ItemDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("keyword");
        String type = request.getParameter("type");

        List<Item> items = itemDAO.searchItems(keyword, type);

        request.setAttribute("items", items);

        request.getRequestDispatcher("items.jsp")
               .forward(request, response);
    }
}