<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Библиотека</title>
    <link rel="stylesheet" href="/styles/page.css">
    <link rel="icon" href="/img/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="/img/logo.png" type="image/x-icon">
</head>
<body>
<jsp:include page="includes/navbar.html"/>
<div style="text-align: center">
    <h1>Библиотека</h1>
    <table style="margin: auto">
        <tr>
            <th>Название</th>
            <th>Автор</th>
            <th>Категория</th>
            <th>Действие</th>
        </tr>
        <c:forEach items="${books}" var="book">
            <tr>
                <td>${book.title}</td>
                <td>${book.authorId}</td>
                <td>${book.categoryId}</td>
                <td>
                    <a href="/books?delete_id=${book.id}">
                        <button class="delete-button">Удалить</button>
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>