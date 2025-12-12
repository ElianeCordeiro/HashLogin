package org.example.repository;

import org.example.user.User;
import org.example.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepository {

    public void save(User user){
        String sql = "INSERT INTO users(username, password) VALUES (?,?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User findByUsername(String username){
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                User u = new User(resultSet.getString("username"), resultSet.getString("password"));
                u.setId(resultSet.getInt("id"));
                return u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
