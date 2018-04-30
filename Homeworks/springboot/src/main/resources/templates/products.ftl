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
<p> Добро пожаловать в сервис совместного планирования.
<p> Каждый пользователь может добавить в общий список покупок свой товар.
<p> Также каждому пользователю доступны рекдактирование введенных позиций и их удаление.
<p> Однако редактировать и удалять он может только тот товар, который сам добавил.
<p> Если же вы обладаете правами администратора, то вы можете удалять любой товар независимо от того, вами он был добавлен или нет.
<#if model.Error??>
<h1>Your ability is limited.</h1>
</#if>
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
