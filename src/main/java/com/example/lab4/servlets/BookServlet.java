package com.example.lab4.servlets;

import com.example.lab4.model.Book;
import com.example.lab4.model.User;
import com.example.lab4.service.db.BookService;
import com.example.lab4.service.db.CategoryService;
import com.example.lab4.service.db.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/books/*")
public class BookServlet extends HttpServlet {
    private final BookService bookService = new BookService();
    private final UserService userService = new UserService();
    private final CategoryService categoryService = new CategoryService();


    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        if (path == null || path.isEmpty()) {
            List<Book> books = bookService.getAllBooks();
            req.setAttribute("books", books);
            req.getRequestDispatcher("/books.jsp").forward(req, resp);
            return;
        }
        String userId = path.split("/")[1];
        User user = userService.getUserById(Integer.parseInt(userId));
        List<Book> books = bookService.getBooksByUserId(Integer.valueOf(userId));
        req.setAttribute("categories", categoryService.getAllCategories());
        req.setAttribute("user", user);
        req.setAttribute("books", books);
        req.getRequestDispatcher("/userCabinet.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Book book =  new Gson().fromJson(requestBody, Book.class);
        if (bookService.addBook(book)) {
            resp.getWriter().println("Success");
        }
        else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonObject error = new JsonObject();
            error.addProperty("message","No user with id "+book.getAuthorId());
            resp.setContentType("application/json");
            resp.getWriter().write(error.toString());
        }
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        int bookId = Integer.parseInt(req.getParameter("id"));
        if (!bookService.deleteBook(bookId)) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Book book =  new Gson().fromJson(requestBody, Book.class);
        if (bookService.editBook(book)) {
            resp.getWriter().println("Success");
        }
        else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonObject error = new JsonObject();
            error.addProperty("message","No expense with id " + book.getId());
            resp.setContentType("application/json");
            resp.getWriter().write(error.toString());
        }
    }
}
