package com.example.zemtsov_7.servlets;

import com.example.zemtsov_7.model.Book;
import com.example.zemtsov_7.model.LibraryCard;
import com.example.zemtsov_7.model.User;
import com.example.zemtsov_7.service.db.BookService;
import com.example.zemtsov_7.service.db.FollowService;
import com.example.zemtsov_7.service.db.LibraryCardService;
import com.example.zemtsov_7.service.db.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet(urlPatterns={"/users/*", "/profile"})
public class UserServlet extends HttpServlet {
    private final UserService userService = new UserService();
    private final BookService bookService = new BookService();
    private final LibraryCardService libraryCardService = new LibraryCardService();
    private final FollowService followService = new FollowService();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        boolean isProfile = Objects.equals(req.getServletPath(), "/profile");
        if ((path == null || path.isEmpty()) && !isProfile) {
            List<User> users = userService.getAllUsers();
            req.setAttribute("users", users);
            req.getRequestDispatcher("/users.jsp").forward(req, resp);
            return;
        }
        String currentUserId = String.valueOf(req.getSession().getAttribute("user_id"));
        String userId = isProfile ? currentUserId : path.split("/")[1];
        User user = userService.getUserById(Integer.parseInt(userId));
        List<Book> books = bookService.getBooksByUserId(Integer.valueOf(userId));
        if(isProfile || Objects.equals(path.split("/")[1], userId)){
            LibraryCard card = libraryCardService.getLibraryCardByUserId(user.getId());
            req.setAttribute("card", card);
        }
        boolean isFollowed = followService.isFollowExists(Integer.parseInt(currentUserId), Integer.parseInt(userId));
        req.setAttribute("isFollowed", isFollowed);
        req.setAttribute("user", user);
        req.setAttribute("books", books);
        req.getRequestDispatcher("/user-acc.jsp").forward(req, resp);
    }
}
