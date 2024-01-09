package com.example.zemtsov_7.service.db;

import com.example.zemtsov_7.model.LibraryCard;
import com.example.zemtsov_7.utils.DataBaseService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LibraryCardService {
    private final DataBaseService dataBaseService;
    static final String SELECT_BY_USER = "SELECT * FROM library_card WHERE user_id=";
    static final String INSERT = "INSERT INTO library_card (user_id, authority) VALUES (?, ?)";
    static final String UPDATE = "UPDATE library_card SET user_id=?, authority=? WHERE id=?";
    static final String DELETE = "DELETE FROM library_card WHERE id=?";

    public LibraryCardService() {
        this.dataBaseService = new DataBaseService();
    }

    public LibraryCard getLibraryCardByUserId(int id) {
        try {
            ResultSet resultSet = dataBaseService.select(SELECT_BY_USER + id);
            if (resultSet.next()) {
                return new LibraryCard(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("authority"),
                        resultSet.getDate("issue_date"),
                        resultSet.getDate("expiry_date")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean addLibraryCard(LibraryCard libraryCard) {
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(INSERT);
            statement.setInt(1, libraryCard.getUserId());
            statement.setString(2, libraryCard.getAuthority());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean updateLibraryCard(LibraryCard libraryCard) {
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(UPDATE);
            statement.setInt(1, libraryCard.getUserId());
            statement.setString(2, libraryCard.getAuthority());
            statement.setInt(3, libraryCard.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean deleteLibraryCard(int id) {
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(DELETE);
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}