package com.example.lab4.servlets;

import com.example.lab4.model.Book;
import com.example.lab4.model.User;
import com.example.lab4.service.db.CategoryService;
import com.example.lab4.service.db.BookService;
import com.example.lab4.service.db.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users/*")
public class UserServlet extends HttpServlet {
    private final UserService userService = new UserService();
    private final CategoryService categoryService = new CategoryService();
    private final BookService bookService = new BookService();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        if (path == null || path.isEmpty()) {
            List<User> users = userService.getAllUsers();
            req.setAttribute("users", users);
            req.getRequestDispatcher("/users.jsp").forward(req, resp);
            return;
        }
        String userId = path.split("/")[1];
        User user = userService.getUserById(Integer.parseInt(userId));
        List<Book> books = bookService.getBooksByUserId(Integer.valueOf(userId));
        req.setAttribute("user", user);
        req.setAttribute("books", books);
        req.getRequestDispatcher("/user-acc.jsp").forward(req, resp);
    }
}
