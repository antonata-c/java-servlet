package com.example.zemtsov_7.service.db;

import com.example.zemtsov_7.model.Category;
import com.example.zemtsov_7.utils.DataBaseService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    private final DataBaseService dataBaseService;
    static final String SELECT_ALL = "SELECT * FROM category ORDER BY id";
    static final String SELECT_ID = "SELECT * FROM category WHERE id=?";
    static final String INSERT = "INSERT INTO category (name) VALUES (?)";
    static final String DELETE = "DELETE FROM category WHERE id=?";
    static final String UPDATE = "UPDATE category SET name=? WHERE id=?";

    public CategoryService() {
        this.dataBaseService = new DataBaseService();
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        try {
            ResultSet resultSet = dataBaseService.select(SELECT_ALL);
            while (resultSet.next()) {
                Category category = new Category(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
                categories.add(category);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return categories;
    }

    public boolean addCategory(Category category) {
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(INSERT);
            statement.setString(1, category.getName());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean editCategory(Category category) {
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(UPDATE);
            statement.setString(1, category.getName());
            statement.setInt(2, category.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean deleteCategory(Integer id) {
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
