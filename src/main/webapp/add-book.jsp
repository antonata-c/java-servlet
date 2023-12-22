<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить книгу</title>
    <link rel="icon" href="/img/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="/img/logo.png" type="image/x-icon">
    <link rel="stylesheet" href="/styles/page.css">
    <link rel="stylesheet" href="/styles/form.css">
</head>
<body>
<jsp:include page="includes/navbar.html"/>
<div class="container">
    <form id="createCategoryForm" action="/books" method="POST">
        <span class="text-center">Добавить книгу</span>
        <div class="input-container">
            <input type="text" id="name" name="title" maxlength="50" required>
            <label for="name">Название</label>
        </div>
        <label for="category_id">Категория</label>
        <div class="select-dropdown">
            <select id="category_id" name="category_id">
                <option value="0">Выберите категорию</option>
                <c:forEach items="${categories}" var="category">
                    <option value="${category.id}">${category.name}</option>
                </c:forEach>
            </select>
        </div>
        <label for="author_id">Автор</label>
        <div class="select-dropdown">
            <select id="author_id" name="author_id">
                <option value="0">Выберите автора</option>
                <c:forEach items="${users}" var="user">
                    <option value="${user.id}">${user.firstName} ${user.lastName}</option>
                </c:forEach>
            </select>
        </div>
        <br>
        <button type="submit">Создать</button>
    </form>
</div>
</body>
</html>
