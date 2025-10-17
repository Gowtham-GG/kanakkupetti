package org.kanakkupetti.util;

import java.sql.*;
import java.util.*;
import org.kanakkupetti.model.User;

public class DatabaseHelper {
    private static final String DB_URL = "jdbc:sqlite:kanakkupetti.db";

    static {
        createUsersTable();
    }

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    private static void createUsersTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                phone TEXT,
                address TEXT
            );
        """;
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<User> getUsers() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = connect();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new User(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("role"),
                        rs.getTimestamp("last_logged_in") != null
                                ? rs.getTimestamp("last_logged_in").toLocalDateTime()
                                : null
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    public static void addUser(String name, String role) {
        String sql = "INSERT INTO users(user_name, role) VALUES('" + name + "', '" + role + "')";
        try (Connection conn = connect(); Statement st = conn.createStatement()) {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editUser(String name, String role, int id) {
        String sql = "UPDATE users SET user_name = '" + name + "',  role='" + role + "' WHERE user_id = " + id;
        try (Connection conn = connect(); Statement st = conn.createStatement()) {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id=?";
        try (Connection conn = connect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
