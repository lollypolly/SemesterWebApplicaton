<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 20.11.2020
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="context" value="${pageContext.request.contextPath}" />
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Котопоиск - поиск</title>
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
    <link rel="stylesheet" type="text/css" href="${context}/css/explorer.css">
</head>
<body>


<div class="d-flex vw-100">
    <div class="vh-100 left-menu">
        <a href="${context}"><img src="${context}/images/cat_left.png" class="cat-image"></a>

        <a href="${context}/p/profile" class="text-body" style="text-decoration: none ">
            <div class="col-12 mx-3">
                <img src="${context}/images/profile_icon.png" class="profile_icon">
                <p>Профиль</p>

            </div>
        </a>
        <a href="${context}/p/collections" class="text-body" style="text-decoration: none ">
            <div class="col-12 mx-3">
                <img src="${context}/images/heart_icon.png" class="heart_icon">
                <p>Мои подборки</p>

            </div>
        </a>


    </div>
    <div class="vh-100 container-fluid" style="background: #FFF5F7">
        <nav class="navbar navbar-light d-flex">
            <div class="d-flex ml-auto align-items-center">
                <div class="mr-sm-2" style="width: 250px">
                    <input class="form-control " id="search-input" type="text" placeholder="Search">
                </div>

                <button class="btn btn-outline my-2 my-sm-0" style="background-color: #FABEBE" id="search">Search
                </button>
                <a href="${context}/p/profile" class="justify-content-center d-flex" style="height: 30px; width: 62px">
                    <c:if test="${pageContext.session.getAttribute('id') == null}">
                        <img src="${context}/images/my_icon.png" style="width: 30px; height: 30px;">
                    </c:if>
                    <c:if test="${pageContext.session.getAttribute('id') != null}">
                        <img src="${context}/uploaded/files?id=${pageContext.session.getAttribute('id')}" style="width: 30px; height: 30px;">
                    </c:if></a>

                <a href="${context}/">
                    <img src="${context}/images/down_icon.png" class="down-icon mx-3"></a>
            </div>
        </nav>
        <div class="row justify-content-center overflow-auto" style="height: 800px;">
            <div id="cat_container_1" class="col-2 like"></div>
            <div id="cat_container_2" class="col-2 like"></div>
            <div id="cat_container_3" class="col-2 like"></div>
            <div id="cat_container_4" class="col-2 like"></div>
            <div id="cat_container_5" class="col-2 like"></div>
        </div>
    </div>
</div>


<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/js/bootstrap.bundle.min.js"
        integrity="sha384-BOsAfwzjNJHrJ8cZidOg56tcQWfp6y72vEJ8xQ9w6Quywb24iOsW913URv1IS4GD"
        crossorigin="anonymous"></script>
<script src="${context}/js/explorer.js"></script>
</body>
</html>
