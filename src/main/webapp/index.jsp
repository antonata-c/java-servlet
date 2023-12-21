<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="/styles/navbar.css">
    <link rel="stylesheet" href="/styles/index.css">
</head>
<body>
    <nav class="navbar">
        <ul>
            <li><a href="/">Главная</a></li>
            <li><a href="/users">Пользователи</a></li>
            <li><a href="/category">Категории покупок</a></li>
        </ul>
    </nav>
    <div style="margin: 20px">
        <h1><%= "Добро пожаловать в приложение!" %>
        </h1>
        <br/>
        <a href="users" >Перейти к списку пользователей</a>
    </div>
</body>
</html>