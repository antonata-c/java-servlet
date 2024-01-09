package com.example.zemtsov_7.servlets;

import com.example.zemtsov_7.model.Book;
import com.example.zemtsov_7.service.db.BookService;
import com.example.zemtsov_7.service.db.CategoryService;
import com.example.zemtsov_7.service.db.UserService;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet("/books/*")
public class BookServlet extends HttpServlet {
    private final BookService bookService = new BookService();
    private final UserService userService = new UserService();
    private final CategoryService categoryService = new CategoryService();


    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("delete_id") != null) {
            doDelete(req, resp);
            return;
        }
        List<Book> books = bookService.getAllBooks();
        req.setAttribute("books", books);
        req.setAttribute("categories", categoryService.getAllCategories());
        req.setAttribute("users", userService.getAllUsers());
        if (Objects.equals(req.getParameter("add"), "true")) {
            req.getRequestDispatcher("/add-book.jsp").forward(req, resp);
            return;
        }
        String path = req.getPathInfo();
        if (path == null || path.isEmpty()) {
            req.getRequestDispatcher("/books.jsp").forward(req, resp);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Book book = new Book(req.getParameter("title"),
                Integer.parseInt(req.getParameter("author_id")),
                Integer.parseInt(req.getParameter("category_id")));
        if (bookService.addBook(book)) {
            resp.sendRedirect("/books");
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Error while creating book");
        }
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int bookId = Integer.parseInt(req.getParameter("delete_id"));
        if (bookService.deleteBook(bookId)) {
            resp.sendRedirect("/books");
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Book book = new Book(req.getParameter("title"),
                Integer.parseInt(req.getParameter("author_id")),
                Integer.parseInt(req.getParameter("category_id")));
        if (bookService.editBook(book)) {
            resp.getWriter().println("Success");
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonObject error = new JsonObject();
            error.addProperty("message", "No expense with id " + book.getId());
            resp.setContentType("application/json");
            resp.getWriter().write(error.toString());
        }
    }
}
