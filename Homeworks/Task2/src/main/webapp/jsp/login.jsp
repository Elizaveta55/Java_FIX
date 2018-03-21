
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
Приветствую, <span>${cookie.nameUser.value}</span>
<p>
<form method="post" action="/login">
    <h3>Введите ваше имя</h3>
    <input type="text" name="name" placeholder="Name">
    <h3>Введите пароль</h3>
    <input type="password" name="password" placeholder="Пароль">
    <input type="submit">
</form>

</body>
</html>
