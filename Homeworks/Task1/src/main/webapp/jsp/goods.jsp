<%--
  Created by IntelliJ IDEA.
  User: Елизавета
  Date: 12.03.2018
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Продукты</title>
</head>
<body>

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
