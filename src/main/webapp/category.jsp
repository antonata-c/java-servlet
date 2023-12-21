<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Категории</title>
    <link rel="stylesheet" href="/styles/userCabinet.css">
    <link rel="stylesheet" href="/styles/navbar.css">
</head>
<body>
    <nav class="navbar">
        <ul>
            <li><a href="/">Главная</a></li>
            <li><a href="/users">Пользователи</a></li>
            <li><a href="/category">Категории покупок</a></li>
        </ul>
    </nav>
    <div class="container">
        <h1>Список категорий покупок</h1>
        <button class="create-button" onclick="openCreateCategoryModal()">Добавить категорию</button>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Название</th>
                <th>Управление</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${categories}" var="category">
                <tr>
                    <td>${category.id}</td>
                    <td>${category.name}</td>
                    <td>
                        <button class="edit-button"
                                onclick="openEditCategoryModal(${category.id},'${category.name}')"
                        >Изменить
                        </button>
                        <button class="delete-button" type="submit" onclick="deleteCategory(${category.id})">Удалить
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div id="createCategoryModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeCreateCategoryModal()">&times;</span>
            <h2>Добавить категорию</h2>
            <form id="createCategoryForm">
                <label for="name">Название</label>
                <input type="text" id="name" name="edit-name" maxlength="50" required>
                <button type="submit">Создать</button>
                <div id="message"></div>
            </form>
        </div>
    </div>
    <div id="editCategoryModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeEditCategoryModal()">&times;</span>
            <h2>Редактировать категорию</h2>
            <form id="editCategoryForm">
                <input type="hidden" id="edit-category-id" name="user_id" required>
                <label for="edit-name">Название</label>
                <input type="text" id="edit-name" name="edit-name" maxlength="50" required>

                <button type="submit">Сохранить</button>
                <div id="edit-message"></div>
            </form>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/scripts/categories.js"></script>
</body>
</html>
