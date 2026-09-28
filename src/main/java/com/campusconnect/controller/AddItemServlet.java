package com.campusconnect.controller;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.campusconnect.dao.ItemDAO;
import com.campusconnect.model.Item;
import com.campusconnect.model.User;

@WebServlet("/add-item")
public class AddItemServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String itemName = request.getParameter("itemName");
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        String location = request.getParameter("location");
        String dateStr = request.getParameter("itemDate");
        String type = request.getParameter("type"); // LOST or FOUND

        Date itemDate;
        try {
            itemDate = Date.valueOf(dateStr); // expects yyyy-MM-dd
        } catch (Exception e) {
            itemDate = new Date(System.currentTimeMillis());
        }

        Item item = new Item(
                user.getId(),
                itemName,
                description,
                category,
                location,
                itemDate,
                type,
                "OPEN"
        );

        ItemDAO itemDAO = new ItemDAO();
        boolean success = itemDAO.addItem(item);

        if (success) {
            response.sendRedirect("items.jsp?posted=true");
        } else {
            request.setAttribute("error", "Could not post item. Please try again.");
            request.getRequestDispatcher("add-item.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("add-item.jsp");
    }
}
