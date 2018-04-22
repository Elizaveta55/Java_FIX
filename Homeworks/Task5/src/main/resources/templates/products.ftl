<#ftl encoding='UTF-8'>
<#import 'spring.ftl' as spring>
<@spring.bind "model"/>
<!doctype html>

<html>
<head>
    <title>Продукты</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<h2>
    <p class="font-weight-light">Приветствую,человек!</p>
</h2>
<p>
<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Name</th>
        <th scope="col">Amount</th>
    </tr>
    </thead>
    <tbody>
    <#list model.goods as good>
    <tr>
        <th scope="row">${good.id}</th>
        <td>${good.name}</td>
        <td>${good.amount}</td>
    </tr>
    </#list>
    </tbody>
</table>

<form action="/products" method="post">
    <input type="text" placeholder="Имя" name="name">
    <input type="number" placeholder="Количество" name="amount">
    <input type="submit">
</form>

</body>
</html>
