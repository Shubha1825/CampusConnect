package com.campusconnect.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.campusconnect.dao.UserDAO;
import com.campusconnect.model.User;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        User user = new User(name, email, password, phone);

        UserDAO userDAO = new UserDAO();

        boolean success = userDAO.registerUser(user);

        if (success) {

            response.sendRedirect("login.jsp?registered=true");

        } else {

            request.setAttribute("error", "Registration failed. That email may already be in use.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("register.jsp");
    }
}
