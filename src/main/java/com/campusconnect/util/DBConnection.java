package com.campusconnect.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {

    private static final Properties props = new Properties();

    static {
        // db.properties lives in src/main/resources and is loaded from the
        // classpath at runtime. It is listed in .gitignore on purpose -
        // your real password never gets committed to version control.
        try (InputStream in = DBConnection.class.getClassLoader()
                .getResourceAsStream("db.properties")) {

            if (in == null) {
                System.err.println(
                    "db.properties not found on classpath. " +
                    "Copy src/main/resources/db.properties.example to " +
                    "src/main/resources/db.properties and fill in your MySQL password."
                );
            } else {
                props.load(in);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {

        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                    props.getProperty("db.url"),
                    props.getProperty("db.user"),
                    props.getProperty("db.password")
            );

            System.out.println("Database connected successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}
