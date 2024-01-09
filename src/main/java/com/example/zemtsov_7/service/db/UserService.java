package com.example.zemtsov_7.service.db;

import com.example.zemtsov_7.model.User;
import com.example.zemtsov_7.utils.DataBaseService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserService {
    private final DataBaseService dataBaseService = new DataBaseService();

    static final String SELECT_ALL = "SELECT * from users ORDER BY id";
    static final String SELECT_BY_ID = "SELECT * from users WHERE id=";
    static final String SELECT_AUTH = "SELECT id FROM users WHERE login = ? AND password = ?";
    static final String SELECT_USER_EXISTS = "SELECT COUNT(*) AS count from users WHERE login=?";
    static final String INSERT = "INSERT INTO users (first_name, last_name, login, password) VALUES (?,?,?,?) RETURNING id";

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            ResultSet resultSet = dataBaseService.select(SELECT_ALL);
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("login"),
                        resultSet.getString("password")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    public User getUserById(int id) {
        try {
            ResultSet resultSet = dataBaseService.select(SELECT_BY_ID + id);
            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("login"),
                        resultSet.getString("password")
                );
            } else {
                throw new IllegalArgumentException("No user with id " + id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public int getUserIdByLoginAndPassword(String login, String password) {
        Connection conn = dataBaseService.getConnect();
        int userId = -1;
        try {
            PreparedStatement statement = conn.prepareStatement(SELECT_AUTH);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userId;
    }
    // TODO: , как раз этим реализовать пост, патч и удаление можно убрать мб, добавить страницу моих подписок, кнопку подписаться и отписаться на других профилях
    public int createUser(String first_name, String last_name, String login, String password) {
        Connection conn = dataBaseService.getConnect();
        int userId = -1;
        try {
            PreparedStatement statement = conn.prepareStatement(INSERT);
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            statement.setString(3, login);
            statement.setString(4, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userId;
    }

    public boolean isUserExistsByLogin(String login) {
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(SELECT_USER_EXISTS);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("count") > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

}
