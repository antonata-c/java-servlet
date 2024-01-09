<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Подписки</title>
    <link rel="stylesheet" href="/styles/page.css">
    <link rel="icon" href="/img/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="/img/logo.png" type="image/x-icon">
</head>
<body>
<jsp:include page="includes/navbar.html"/>
<div style="text-align: center">
    <h1>Подписки</h1>
    <h3>Общее количество подписок: ${follows_count}.</h3>
    <table style="margin: auto">
        <tr>
            <th>Имя</th>
            <th>Фамилия</th>
            <th>Дата начала подписки</th>
        </tr>
        <c:forEach items="${follows}" var="follow">
            <tr onclick="window.location.href='http://localhost:8080/users/${follow.followingId}'" style="cursor: pointer;">
                <td>${follow.followingFirstName}</td>
                <td>${follow.followingLastName}</td>
                <td>${follow.startDate}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
