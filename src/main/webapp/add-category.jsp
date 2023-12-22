<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить категорию</title>
    <link rel="stylesheet" href="/styles/category.css">
</head>
<body>
    <div class="modal">
        <div class="modal-content">
            <h2>Добавить категорию</h2>
            <form id="createCategoryForm" action="/category" method="POST">
                <label for="name">Название</label>
                <input type="text" id="name" name="name" maxlength="50" required>
                <button type="submit">Создать</button>
                <div id="message"></div>
            </form>
        </div>
    </div>
</body>
</html>
