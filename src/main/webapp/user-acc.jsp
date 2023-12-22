<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Личный кабинет</title>
    <link rel="icon" href="/img/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="/img/logo.png" type="image/x-icon">
    <link rel="stylesheet" href="/styles/page.css">
</head>
<body>
    <jsp:include page="includes/navbar.html" />
    <div class="container">
        <h1>Страница автора ${user.firstName}!</h1>
        <table>
            <caption><h2>Список книг</h2></caption>
            <thead>
            <tr>
                <th>ID</th>
                <th>Название</th>
                <th>Категория</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${books}" var="book">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.title}</td>
                    <td>${book.categoryId}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
