package com.campusconnect.util;

import java.sql.Connection;

public class TestConnection {

    public static void main(String[] args) {

        Connection connection = DBConnection.getConnection();

        if (connection != null) {
            System.out.println("SUCCESS: MySQL connection is working!");
        } else {
            System.out.println("FAILED: Could not connect to MySQL.");
        }
    }
}