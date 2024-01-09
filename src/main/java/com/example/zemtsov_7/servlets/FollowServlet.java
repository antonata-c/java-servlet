package com.example.zemtsov_7.servlets;

import com.example.zemtsov_7.model.Follow;
import com.example.zemtsov_7.service.db.FollowService;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet("/follow")
public class FollowServlet extends HttpServlet {
    private final FollowService followService = new FollowService();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("delete_id") != null) {
            doDelete(req, resp);
            return;
        }
        String userId = String.valueOf(req.getSession().getAttribute("user_id"));
        List<Follow> follows = followService.getAllFollows(Integer.parseInt(userId));
        req.setAttribute("follows", follows);
        req.setAttribute("follows_count", follows.size());
        req.getRequestDispatcher("/follows.jsp").forward(req, resp);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String followerId = String.valueOf(req.getSession().getAttribute("user_id"));
        String followingId = req.getParameter("following_id");
        if (Objects.equals(followingId, followerId)){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("You can't subscribe yourself");
        }
        Follow follow = new Follow(
                Integer.parseInt(followerId),
                Integer.parseInt(followingId)
        );
        if (followService.addFollow(follow)) {
            resp.sendRedirect("/users/" + followingId);
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Error while creating follow");
        }
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int followingId = Integer.parseInt(req.getParameter("delete_id"));
        if (followService.deleteFollow(Integer.parseInt(String.valueOf(req.getSession().getAttribute("user_id"))), followingId)) {
            resp.sendRedirect("/users/" + followingId);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}
