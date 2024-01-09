package com.example.zemtsov_7.servlets;

import com.example.zemtsov_7.model.Category;
import com.example.zemtsov_7.service.db.CategoryService;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends HttpServlet {
    private final CategoryService categoryService = new CategoryService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("delete_id") != null){
            doDelete(req, resp);
            return;
        }
        List<Category> categories = categoryService.getAllCategories();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/category.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path = req.getPathInfo();
        if (path != null) {
            doPut(req, resp);
            return;
        }
        Category category = new Category(req.getParameter("name"));
        if (categoryService.addCategory(category)) {
            resp.sendRedirect("/category");
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Error while creating category");
        }
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int categoryId = Integer.parseInt(req.getParameter("delete_id"));
        if (categoryService.deleteCategory(categoryId)) {
            resp.sendRedirect("/category");
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Category category = new Category(Integer.parseInt(req.getParameter("id")), req.getParameter("name"));
        if (categoryService.editCategory(category)) {
            resp.sendRedirect("/category");
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonObject error = new JsonObject();
            error.addProperty("message", "No expense with id " + category.getId());
            resp.setContentType("application/json");
            resp.getWriter().write(error.toString());
        }
    }
}
