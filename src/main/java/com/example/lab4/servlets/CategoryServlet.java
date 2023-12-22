package com.example.lab4.servlets;

import com.example.lab4.model.Category;
import com.example.lab4.service.db.CategoryService;
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
    private final CategoryService categoryService;

    public CategoryServlet() {
        this.categoryService = new CategoryService();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = categoryService.getAllCategories();
        req.setAttribute("categories",categories);
        req.getRequestDispatcher("/category.jsp").forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path = req.getPathInfo();
        if (path != null) {
            doPut(req, resp);
            return;
        }
        Category category = new Category(req.getParameter("name"));
        if (categoryService.addCategory(category)) {
            resp.getWriter().println("Success");
        }
        else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Error while creating category");
        }
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        int categoryId = Integer.parseInt(req.getParameter("id"));
        if (!categoryService.deleteCategory(categoryId)) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Category category = new Category(Integer.parseInt(req.getParameter("id")), req.getParameter("name"));
        if (!categoryService.editCategory(category)){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonObject error = new JsonObject();
            error.addProperty("message","No expense with id "+category.getId());
            resp.setContentType("application/json");
            resp.getWriter().write(error.toString());
        }
    }
}
