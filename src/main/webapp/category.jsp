<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Категории</title>
    <link rel="stylesheet" href="/styles/page.css">
</head>
<body>
    <jsp:include page="includes/navbar.html" />
    <div class="container">
        <h1>Список категорий</h1>
        <a href="add-category.jsp">
            <button class="create-button">Добавить категорию</button>
        </a>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Название</th>
                <th>Действие</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${categories}" var="category">
                <tr>
                    <td>${category.id}</td>
                    <td>${category.name}</td>
                    <td>
                        <a href="edit-category.jsp?id=${category.id}">
                            <button class="edit-button">Редактировать</button>
                        </a>
                        <a href="/category?delete_id=${category.id}">
                            <button class="delete-button">Удалить</button>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
