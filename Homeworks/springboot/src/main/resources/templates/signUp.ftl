<#ftl encoding='UTF-8'>
<#import 'spring.ftl' as spring>
<head>
    <title>Sign Up</title>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="/css/main.css" />
    <!--[if lte IE 8]><link rel="stylesheet" href="/css/ie8.css" /><![endif]-->
    <!--[if lte IE 9]><link rel="stylesheet" href="/css/ie9.css" /><![endif]-->
</head>
<body>

<div id="page-wrapper">

    <div id="header">
        <div>
            <section id="banner">
                <form method="post" action="/signUp" style="position: absolute; left: 470px">
                    <h3>Введите адрес электронной почты</h3>
                    <input type="text" name="login" placeholder="Login">
                    <h3>Придумайте надежный пароль</h3>
                    <input type="password" name="password" placeholder="Пароль">
                    <h3>Повторите ваш пароль</h3>
                    <input type="password" name="checkPassword" placeholder="Повторите">
                    <p>
                    <input type="submit">
                </form>
            </section>
        </div>
    </div>
</div>
</body>