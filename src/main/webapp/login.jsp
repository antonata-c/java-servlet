<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход</title>
    <link rel="stylesheet" href="/styles/login.css">
</head>
<body>
<div class="login-container">
    <h2>Авторизация</h2>
    <form action="/login" method="post">
        <label for="username">Логин:</label>
        <input type="text" id="username" name="username" required>
        <br>
        <label for="password">Пароль:</label>
        <input type="password" id="password" name="password" required>
        <br>
        <button type="submit">Войти</button>
        <p>У вас нет аккаунта? <a href="/registration">Зарегистрироваться</a></p>
    </form>
    <div class="error-message">${errorMessage}</div>
</div>
</body>

</html>
