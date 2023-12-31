<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Пользователи</title>
    <link rel="stylesheet" href="styles/users.css">
    <link rel="icon" href="/img/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="/img/logo.png" type="image/x-icon">
</head>
<body>
    <jsp:include page="includes/navbar.html" />
    <div style="text-align: center">
        <h1>Список авторов</h1>
        <table style="margin: auto">
            <tr>
                <th>ID</th>
                <th>Имя</th>
                <th>Фамилия</th>
            </tr>
            <c:forEach items="${users}" var="user">
            <tr onclick="window.location.href='http://localhost:8080/users/${user.id}'" style="cursor: pointer;">
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
            </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
