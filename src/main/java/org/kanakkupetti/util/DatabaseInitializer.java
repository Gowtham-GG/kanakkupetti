package org.kanakkupetti.util;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initialize() {
        String createItemsTable = """
            CREATE TABLE IF NOT EXISTS items (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                price REAL NOT NULL
            );
        """;

        try (Connection conn = DatabaseUtil.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createItemsTable);
            System.out.println("Database initialized successfully.");
        } catch (Exception e) {
            System.err.println("Database initialization failed: " + e.getMessage());
        }
    }
}