package com.example.zemtsov_7.servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        boolean isLoggedIn = (session != null && session.getAttribute("user_id") != null);
        String requestURI = httpRequest.getRequestURI();
        if (!isLoggedIn && !requestURI.endsWith("/login") && !requestURI.endsWith("/registration") && !requestURI.endsWith(".css")) {
            httpResponse.sendRedirect("/login");
            return;
        }
        chain.doFilter(request, response);
    }


}