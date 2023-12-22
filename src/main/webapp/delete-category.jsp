<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Удалить категорию</title>
    <link rel="stylesheet" href="/styles/category.css">
</head>
<body>
<div class="modal">
    <div class="modal-content">
        <h2>Вы уверены, что хотите удалить категорию?</h2>
        <form id="deleteCategoryForm" action="/category/${param.get("id")}" method="POST">
            <input type="hidden" name="id" value="${param.get("id")}" />
            <label for="name">Название</label>
            <input type="text" id="name" name="name" maxlength="50" required>
            <button type="submit">Сохранить</button>
            <div id="edit-message"></div>
        </form>
    </div>
</div>
</body>
</html>
