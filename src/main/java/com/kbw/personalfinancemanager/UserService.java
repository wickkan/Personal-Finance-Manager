package com.kbw.personalfinancemanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    private static final String INSERT_USER_SQL = "INSERT INTO users(name, email, password) VALUES(?, ?, ?)";
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";

    public boolean registerUser(User user) {
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_USER_SQL)) {

            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword()); // For simplicity, storing plain password. In production, hash passwords!

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public User getUserByEmail(String email) {
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_USER_BY_EMAIL)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                User user = new User(rs.getString("name"), rs.getString("email"), rs.getString("password"));
                user.setId(rs.getInt("id"));
                return user;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
