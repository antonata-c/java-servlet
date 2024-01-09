package com.example.zemtsov_7.servlets;

import com.example.zemtsov_7.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final AuthService authService;

    public LoginServlet() {
        this.authService = new AuthService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("errorMessage", "");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
        resp.setContentType("text/html");
        super.doGet(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        int userId = authService.auth(username, password);
        if (userId != -1) {
            req.getSession().setAttribute("user_id", userId);
            resp.sendRedirect( "/");
        } else {
            req.setAttribute("errorMessage", "Неверный логин или пароль!");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            super.doPost(req, resp);
        }
    }
}
