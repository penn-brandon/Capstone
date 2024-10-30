<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BLURB | Home Page</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Anta&display=swap" rel="stylesheet">

    <script src="${pageContext.request.contextPath}/javascript/theme.js"></script>
</head>

<body>
<nav>
    <div class="nav-content">
        <a href="${pageContext.request.contextPath}/index">
            <div class="nav-img">
                <img src="${pageContext.request.contextPath}/images/logo.svg" alt="Logo"/>
                <span class="nav-logo">BLURB</span>
            </div>
        </a>
    </div>
</nav>

<div class="main">
    <h1>BLURB</h1>
    <p>
        A chatting app that is simply a prototype built by some people who love
        coding.
    </p>
    <a href="${pageContext.request.contextPath}/login">
        <button class="nav-login-login" id="toLogin">Login</button>
    </a>
    <a href="${pageContext.request.contextPath}/signup">
        <button class="nav-login-register" id="toSignup">Register</button>
    </a>
</div>

<footer>
    <div class="footer-div">
        <p class="footer-copyright">
            Copyright of Cool Dudes &copy;2024. All Rights Reserved.
        </p>
    </div>
</footer>
</body>

</html>