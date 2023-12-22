<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Библиотека</title>
    <link rel="stylesheet" href="/styles/books.css">
    <link rel="stylesheet" href="/styles/navbar.css">
    <link rel="icon" href="/img/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="/img/logo.png" type="image/x-icon">
</head>
<body>
    <nav class="navbar">
        <ul>
            <li><a href="/">Главная</a></li>
            <li><a href="/books">Библиотека</a></li>
            <li><a href="/category">Категории книг</a></li>
            <li><a href="/users">Пользователи</a></li>
        </ul>
    </nav>
    <div style="text-align: center">
        <h1>Библиотека</h1>
        <table style="margin: auto">
            <tr>
                <th>Название</th>
                <th>Категория</th>
                <th>Автор</th>
            </tr>
            <c:forEach items="${books}" var="book">
                <tr>
                    <td>${book.title}</td>
                    <td>${book.authorId}</td>
                    <td>${book.categoryId}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>