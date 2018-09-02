<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>登录</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link href="/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="/css/animate.css" rel="stylesheet">
    <link href="/css/style.css?v=4.1.0" rel="stylesheet">
</head>

<body class="gray-bg">

<div class="middle-box text-center loginscreen  animated fadeInDown">
    <div>
        <div>
            <h1 class="logo-name">Hello</h1>
        </div>
        <h3>欢迎使用 ${basename!''}</h3>
        <#if error?? >
        <h4 style="color: darkred;">${error}</h4>
        </#if>

        <form class="m-t" role="form" method="post" action="/login/submit.htm">
            <div class="form-group">
                <input type="text" name="username" class="form-control" placeholder="用户名" required>
            </div>
            <div class="form-group">
                <input type="password" name="password" class="form-control" placeholder="密码" required>
            </div>
            <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>
            <!--            <p class="text-muted text-center">
                           <a href="login.html#"><small>忘记密码了？</small></a> | <a href="register.html">注册一个新账号</a>
                       </p> -->
        </form>
    </div>
</div>

<!-- Mainly scripts -->
<script src="/js/jquery.min.js?v=2.1.4"></script>
<script src="/js/bootstrap.min.js?v=3.4.0"></script>
</body>
</html>