<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить категорию</title>
    <link rel="icon" href="/img/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="/img/logo.png" type="image/x-icon">
    <link rel="stylesheet" href="/styles/form.css">
</head>
<body>
<jsp:include page="includes/navbar.html"/>
<div class="container">
    <form id="createCategoryForm" action="/category" method="POST">
        <span class="text-center">Добавить категорию</span>
        <div class="input-container">
            <input type="text" id="name" name="name" maxlength="50" required>
            <label for="name">Название</label>
        </div>
        <button type="submit">Создать</button>
    </form>
</div>
</body>
</html>
