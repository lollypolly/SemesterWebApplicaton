<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="context" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Котопоиск</title>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;400;500;600;700&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${context}/css/style.css">
</head>
<body>
<div class="d-flex gradient w-100">

    <nav class="navbar fixed-top justify-content-between">
        <a class="navbar-brand">
            <img src="images/Logo.png" style="width: 251px;
height: 43px;"></a>
        <div class="d-flex justify-content-end">
            <a href="${context}/p/signIn">
                <div class="my-2 my-sm-0 ml-auto btn-sign-in d-flex align-items-center">
                    <p class="mx-auto my-auto">Sign in</p></div>
            </a>

            <a href="${context}/p/signUp">
                <div class="my-2 my-sm-0 mx-2 btn-sign-up d-flex align-items-center">
                    <p class="mx-auto my-auto ">Sign up</p></div>
            </a>
        </div>

    </nav>

    <img src="images/cat.png" class="cat-image mx-auto d-block" style="width: 752px;height: 240px;">
</div>

<div class="text-center justify-content-between ">
    <h1 class="title-for-cat">Котопоиск — отыщи своего </h1>
</div>

<div>

</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
        crossorigin="anonymous"></script>

</body>
</html>
