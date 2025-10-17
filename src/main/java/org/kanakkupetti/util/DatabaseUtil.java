package org.kanakkupetti.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String DB_URL = "jdbc:sqlite:kanakkupetti.db";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.err.println("Connection to SQLite failed: " + e.getMessage());
            return null;
        }
    }
}