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
<jsp:include page="includes/navbar.html"/>
<div class="container">
    <c:choose>
        <c:when test="${user.id != user_id}">
            <h1>Страница автора ${user.firstName}</h1>
            <c:choose>
                <c:when test="${!isFollowed}">
                    <form method="post" action="/follow">
                        <input type="hidden" name="following_id" value="${user.id}">
                        <button type="submit">Подписаться</button>
                    </form>
                </c:when>
                <c:otherwise>
                    <a href="/follow?delete_id=${user.id}"><button>Отменить подписку</button></a>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
            <h1>Ваш профиль, ${user.firstName}</h1>
            <c:choose>
                <c:when test="${card != null}">
                    <div class="reader-card">
                        <h2 class="card-title">Читательский билет</h2>
                        <div class="card-info">
                            <span class="label">Выдан:</span>
                            <span class="value">${card.authority}</span>
                            <br>
                            <span class="label">Дата выдачи:</span>
                            <span class="value">${card.issueDate}</span>
                            <br>
                            <span class="label">Дата окончания:</span>
                            <span class="value">${card.expiryDate}</span>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <form method="post" action="/library-card">
                        <button type="submit">Получить читательский билет</button>
                    </form>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
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
                <td>${book.categoryName}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
