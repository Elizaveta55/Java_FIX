
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Зарегистрироваться</title>
</head>
<body>
Приветствую, <span>${cookie.nameUser.value}</span>
<p>
<form method="post" action="/signUp">
    <h3>Введите уникальное имя</h3>
    <input type="text" name="name" placeholder="Name">
    <h3>Придумайте надежный пароль</h3>
    <input type="password" name="password" placeholder="Пароль">
    <h3>Повторите ваш пароль</h3>
    <input type="password" name="checkPassword" placeholder="Повторите">
    <input type="submit">
</form>

</body>
</html>
