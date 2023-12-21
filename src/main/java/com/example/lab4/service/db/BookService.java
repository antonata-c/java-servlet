package com.example.lab4.service.db;

import com.example.lab4.model.Book;
import com.example.lab4.utils.DataBaseService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookService {
    private final DataBaseService dataBaseService;
    static final String SELECT_BY_USERID = "SELECT * from book JOIN category" +
            " on category.id=book.category_id where book.author_id=?";
    static final String INSERT = "INSERT INTO book (title, author_id, category_id) values (?,?,?)";
    static final String DELETE = "DELETE FROM book where id=?";
    static final String UPDATE = "UPDATE book SET title=?, author_id=?, category_id=? where id=?";
// TODO: сделать getallbooks и отдельной вкладкой, переписать все на другой лад, зафиксить фронт
    public BookService() {
        this.dataBaseService = new DataBaseService();
    }
    public List<Book> getBooksByUserId(Integer userId) {
        List<Book> books = new ArrayList<>();
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(SELECT_BY_USERID);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getInt("author_id"),
                        resultSet.getInt("category_id")
                );
                books.add(book);
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return books;
    }
    public boolean addBook(Book book) {
        return bookActions(book, INSERT);
    }
    public boolean editBook(Book book) {
        return bookActions(book, UPDATE);
    }

    private boolean bookActions(Book book, String method) {
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(method);
            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getAuthorId());
            statement.setInt(3, book.getCategoryId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean deleteBook(Integer id) {
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
