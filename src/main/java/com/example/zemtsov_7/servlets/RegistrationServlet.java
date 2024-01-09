package com.example.zemtsov_7.servlets;

import com.example.zemtsov_7.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private final AuthService authService;

    public RegistrationServlet() {
        this.authService = new AuthService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("errorMessage", "");
        req.getRequestDispatcher("/registration.jsp").forward(req, resp);
        resp.setContentType("text/html");
        super.doGet(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String first_name = req.getParameter("first_name");
        String last_name = req.getParameter("last_name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        int userId = authService.register(first_name, last_name,login,password);
        if (userId != -1) {
            req.getSession().setAttribute("user_id", userId);
            resp.sendRedirect( "/");
        } else {
            req.setAttribute("errorMessage", "Логин занят!");
            req.getRequestDispatcher("/registration.jsp").forward(req, resp);
            super.doPost(req, resp);
        }
    }
}
