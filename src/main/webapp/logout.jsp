<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.removeAttribute("userId");
    session.invalidate();
    response.sendRedirect("/login");
%>