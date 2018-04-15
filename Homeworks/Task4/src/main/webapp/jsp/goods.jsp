
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Продукты</title>
</head>
<body>
Приветствую,<span>${cookie.name.value}</span>
<p>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Amount</th>
    </tr>
    <c:forEach items="${goods}" var="good">
        <tr>
           <td>${good.id}</td>
           <td>${good.name}</td>
           <td>${good.amount}</td>
        </tr>
    </c:forEach>
</table>

<form action="/products" method="post">
    <input type="text" placeholder="Имя" name="name">
    <input type="text" placeholder="Количество" name="amount">
    <input type="submit">
</form>

</body>
</html>
