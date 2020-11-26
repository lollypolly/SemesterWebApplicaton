<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="context" value="${pageContext.request.contextPath}" />
<html>
<head>
    <meta charset="UTF-8">
    <title>Котопоиск - коллекция</title>
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
        <nav class="navbar navbar-light d-flex align-items-start">
            <div class="mx-3">
                <c:if test="${pageContext.session.getAttribute('id') == null}">
                    <img src="${context}/images/icon.png" style="width: 200px; height: 200px;">
                </c:if>
                <c:if test="${pageContext.session.getAttribute('id') != null}">
                    <img src="${context}/uploaded/files?id=${pageContext.session.getAttribute('id')}" style="width: 200px; height: 200px;">
                </c:if>
            </div>
            <div class="mx-3 my-3">
                <a style="font-family: Lobster;font-size: 54px">${pageContext.session.getAttribute('lastName')} ${pageContext.session.getAttribute('firstName')}</a>
                <p style="font-size: 24px;color: #7C7C7C">@${pageContext.session.getAttribute('username')}</p>
            </div>

            <form class="d-flex ml-auto align-items-center ">

                <a href="${context}/p/explore">
                    <img src="${context}/images/house.png" class="house mx-3"></a>

                <img src="${context}/images/down_icon.png" class="down-icon mx-3">
            </form>
        </nav>
        <hr class="solid" style="height: 2px;color: #7C7C7C; margin: revert">
        <div>
            <nav class="d-flex">
                <img src="${context}/images/edit_icon.png" class="down-icon mx-3">
                <img src="${context}/images/add_icon.png" class="down-icon ml-auto mx-3">
                <img src="${context}/images/settings_icon.png" class="down-icon mx-3">

            </nav>
        </div>
        <div class="row justify-content-start overflow-auto" id="Liked" style="height: 700px;">

        </div>
    </div>
</div>


<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/js/bootstrap.bundle.min.js"
        integrity="sha384-BOsAfwzjNJHrJ8cZidOg56tcQWfp6y72vEJ8xQ9w6Quywb24iOsW913URv1IS4GD"
        crossorigin="anonymous"></script>
<script src="${context}/js/collections.js"></script>
</body>
</html>
