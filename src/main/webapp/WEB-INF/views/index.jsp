<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Sign Up</title>
    <link rel="stylesheet" href="../../../resources/static/css/max-theme.css" media="(width >= 750px)" />
    <link rel="stylesheet" href="../../../resources/static/css/min-theme.css" media="(width < 750px)" />

    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link href="https://fonts.googleapis.com/css2?family=Anta&display=swap" rel="stylesheet" />

    <script defer type="text/javascript" src="../../../resources/static/javascript/min-theme.js"></script>
</head>

<body>
<nav>
    <div class="nav-content">
        <div class="nav-img">
            <img src="../../../resources/static/images/logo.svg" />
            <span class="random">BLURB</span>
        </div>
        <div class="nav-login">
            <a href="./html/signin.html"><button class="nav-login-login">Login</button></a>
            <a href="./html/signup.html"><button class="nav-login-register">Register</button></a>
        </div>
        <div id="nav-dropdown" class="nav-dropdown">
            <img id="hamburger" class="hamburger" src="./images/hamburger.svg" onclick="hamburgers()">
        </div>
    </div>
</nav>

<div class="main">
    <h1>BLURB</h1>
    <p>
        A chatting app that is simply a prototype built by some people who love
        coding.
    </p>
</div>

<footer>
    <div class="footer-content">
        <div class="footer-image">
            <img src="../../../resources/static/images/logo.svg" alt="Logo" />
            <span class="random">BLURB</span>
        </div>
        <p class="footer-copyright">
            Copyright of Cool Dudes &copy;2024. All Rights Reserved.
        </p>
    </div>
</footer>
</body>

</html>