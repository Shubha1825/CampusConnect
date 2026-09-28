package com.campusconnect.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.campusconnect.dao.ClaimDAO;
import com.campusconnect.model.Claim;
import com.campusconnect.model.User;

@WebServlet("/claim")
public class ClaimServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        User user = (session != null)
                ? (User) session.getAttribute("user")
                : null;


        // Check whether user is logged in
        if (user == null) {

            response.sendRedirect("login.jsp");

            return;
        }


        // Get item ID
        int itemId;

        try {

            itemId = Integer.parseInt(
                request.getParameter("itemId")
            );

        } catch (NumberFormatException e) {

            response.sendRedirect("items");

            return;
        }


        // Get claim message
        String message = request.getParameter("message");


        // Create Claim object
        Claim claim = new Claim(
            itemId,
            user.getId(),
            message
        );


        // Save claim
        ClaimDAO claimDAO = new ClaimDAO();

        boolean success = claimDAO.addClaim(claim);


        // Go back through ItemListServlet
        response.sendRedirect(
            "items?claimed=" + success
        );
    }


    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect("items");
    }
}