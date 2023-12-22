<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактировать категорию</title>
    <link rel="icon" href="/img/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="/img/logo.png" type="image/x-icon">
    <link rel="stylesheet" href="/styles/page.css">
    <link rel="stylesheet" href="/styles/form.css">
</head>
<body>
<jsp:include page="includes/navbar.html"/>
<div class="container">
    <form id="editCategoryForm" action="/category/${param.get("id")}" method="POST">
        <span class="text-center">Редактировать категорию</span>
        <input type="hidden" name="id" value="${param.get("id")}"/>
        <div class="input-container">
            <input type="text" id="name" name="name" maxlength="50" required>
            <label for="name">Название</label>
        </div>
        <button type="submit">Сохранить</button>
    </form>
</div>
</body>
</html>
