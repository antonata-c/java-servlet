<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
    <link rel="stylesheet" href="/styles/login.css">
</head>
<body>
<div class="login-container">
    <h2>Форма регистрации</h2>
    <form action="/registration" method="post">
        <label for="first_name">Имя:</label>
        <input type="text" id="first_name" name="first_name" maxlength="50" required>
        <br>
        <label for="last_name">Фамилия:</label>
        <input type="text" id="last_name" name="last_name" maxlength="50" required>
        <br>
        <label for="login">Логин:</label>
        <input type="text" id="login" name="login" maxlength="50" required>
        <br>
        <label for="password">Пароль:</label>
        <input type="password" id="password" name="password" maxlength="50" required>
        <br>
        <button type="submit">Зарегистрироваться</button>
        <p>Уже зарегистрированы? &nbsp;<a href="/login">Вход</a></p>
    </form>
    <div class="error-message">${errorMessage}</div>
</div>
</body>
</html>