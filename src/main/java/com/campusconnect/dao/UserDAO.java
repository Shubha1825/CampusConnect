package com.campusconnect.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.campusconnect.model.User;
import com.campusconnect.util.DBConnection;
import com.campusconnect.util.PasswordUtil;

public class UserDAO {

    // Register a new user (password is hashed before storage)
    public boolean registerUser(User user) {

        // Prevent duplicate email registration
        if (emailExists(user.getEmail())) {
            return false;
        }

        String sql = "INSERT INTO users (name, email, password, phone) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, PasswordUtil.hash(user.getPassword()));
            ps.setString(4, user.getPhone());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean emailExists(String email) {

        String sql = "SELECT id FROM users WHERE email = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Login user: fetch by email only, then verify hash in Java
    public User loginUser(String email, String password) {

        String sql = "SELECT * FROM users WHERE email = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    String storedHash = rs.getString("password");

                    if (PasswordUtil.verify(password, storedHash)) {

                        User user = new User();
                        user.setId(rs.getInt("id"));
                        user.setName(rs.getString("name"));
                        user.setEmail(rs.getString("email"));
                        user.setPassword(storedHash);
                        user.setPhone(rs.getString("phone"));

                        return user;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
