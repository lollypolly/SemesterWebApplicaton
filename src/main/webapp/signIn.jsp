<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Котопоиск - вход</title>
    <link rel="apple-touch-icon" sizes="76x76" href="${context}/assets/apple-touch-icon.png">
    <link rel="icon" href="${context}/assets/favicon.ico">
    <link rel="icon" type="image/png" sizes="32x32" href="${context}/assets/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="${context}/assets/favicon-16x16.png">
    <link rel="manifest" href="${context}/assets/site.webmanifest">
    <link rel="mask-icon" href="${context}/assets/safari-pinned-tab.svg" color="#5bbad5">
    <meta name="msapplication-TileColor" content="#da532c">
    <meta name="theme-color" content="#ffffff">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/css/bootstrap.min.css"
          integrity="sha384-DhY6onE6f3zzKbjUPRc2hOzGAdEf4/Dz+WJwBvEYL/lkkIsI3ihufq9hk9K4lVoK" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;400;500;600;700&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${context}/css/signIn.css">
</head>
<body>
<div class="main d-flex flex-column justify-content-between">
    <div class="d-flex justify-content-center oval">
        <c:if test="${pageContext.session.getAttribute('id') != null}">
            <a href="${context}/p/profile">Перейти в профиль</a>
        </c:if>
        <c:if test="${pageContext.session.getAttribute('id') == null}">
            <form action="${context}/p/signIn" method="post">
                <h1 class="text-center" style="font-family: 'Lobster', cursive; font-size: 72px; line-height: 90px;">С
                    возвращением</h1>
                <div class="row justify-content-center mt-3">
                    <div class="form-group row my-3">
                        <label for="inputNick" class="col-2 col-form-label text-right">Ник</label>
                        <div class="col-8">
                            <input type="text" name="username" class="form-control h-100" id="inputNick" required>
                        </div>
                    </div>
                    <div class="form-group row my-3">
                        <label for="inputEmail" class="col-2 col-form-label text-right">Email</label>
                        <div class="col-8">
                            <input type="email" name="email" class="form-control h-100" id="inputEmail" required>
                        </div>
                    </div>
                    <div class="form-group row my-3">
                        <label for="inputPassword" class="col-2 col-form-label text-right">Пароль</label>
                        <div class="col-8">
                            <input type="password" name="password" class="form-control h-100" id="inputPassword"
                                   required>
                        </div>
                    </div>
                </div>
                <div class="form-group row my-3">
                    <div class="col-12 row d-flex justify-content-center">
                        <button type="submit" class="btn col-4" id="asseptSignIn" style="background-color: #FEBCFF;">
                            Sign in
                        </button>
                    </div>
                </div>
            </form>
        </c:if>
    </div>
    <div class="d-flex justify-content-center">
        <img src="${context}/images/cat.png" alt="" style="width: 930px; height: 300px;">
    </div>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/js/bootstrap.bundle.min.js"
        integrity="sha384-BOsAfwzjNJHrJ8cZidOg56tcQWfp6y72vEJ8xQ9w6Quywb24iOsW913URv1IS4GD"
        crossorigin="anonymous"></script>
</body>
</html>
