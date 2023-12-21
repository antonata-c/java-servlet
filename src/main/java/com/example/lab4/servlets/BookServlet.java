package com.example.lab4.servlets;

import com.example.lab4.model.Book;
import com.example.lab4.service.db.BookService;
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

@WebServlet("/book/*")
public class BookServlet extends HttpServlet {
    private final BookService bookService;

    public BookServlet() {
        this.bookService = new BookService();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        if (path == null || path.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String userId = req.getPathInfo().split("/")[1];
        List<Book> books = bookService.getBooksByUserId(Integer.parseInt(userId));
        req.setAttribute("books", books);
        req.getRequestDispatcher("/finance-history.jsp").forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bookId = Integer.parseInt(req.getParameter("id"));
        if (!bookService.deleteBook(bookId)) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
