package com.example.zemtsov_7.servlets;

import com.example.zemtsov_7.model.LibraryCard;
import com.example.zemtsov_7.service.db.LibraryCardService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/library-card")
public class LibraryCardServlet extends HttpServlet {
    private final LibraryCardService libraryCardService = new LibraryCardService();

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LibraryCard card = new LibraryCard(
                Integer.parseInt(String.valueOf(req.getSession().getAttribute("user_id"))),
                "Онлайн библиотека"
        );
        if (libraryCardService.addLibraryCard(card)) {
            resp.sendRedirect("/profile");
        }else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Error while creating library card");
        }

    }
}
