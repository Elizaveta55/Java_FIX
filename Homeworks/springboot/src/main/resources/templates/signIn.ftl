<#ftl encoding='UTF-8'>
<#import 'spring.ftl' as spring>
<@spring.bind "model"/>
<head>
    <title>Sign In</title>
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
            <#if model.error??>
                <h1>There is mistake. Please check your login or password and try it again.</h1>
            </#if>
                <form method="post" action="/signIn" style="position: absolute; top: 100px; left: 350px">
                    <input type="text" size="100" name="login" placeholder="login">
                    <input type="password" name="password" placeholder="Пароль">
                    <input type="checkbox" name="remember-me" placeholder="Запомнить"> Запомнить меня<p>
                    <input type="submit">
                </form>
            </section>
        </div>
    </div>
</div>
</body>