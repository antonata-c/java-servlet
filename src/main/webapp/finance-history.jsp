<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>История доходов/расходов</title>
    <link rel="stylesheet" href="/styles/finance-history.css">
    <link rel="icon" href="/img/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="/img/logo.png" type="image/x-icon">
</head>
<body>
    <div style="text-align: center">
        <h1>Таблица расходов</h1>
        <table style="margin: auto">
            <tr>
                <th>Сумма</th>
                <th>Описание</th>
                <th>Категория</th>
                <th>Дата</th>
            </tr>
            <c:forEach items="${expenses}" var="expense">
            <tr>
                <td>${expense.amount}</td>
                <td>${expense.source}</td>
                <td>${expense.category}</td>
                <td>${expense.dateFormatted}</td>
            </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
