<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Page</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme.css"/>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Anta&display=swap" rel="stylesheet">

    <script defer src="${pageContext.request.contextPath}/javascript/register.js"></script>
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
    <div class="signup-form">
        <h1>Get Started Today</h1>
        <p>This is a cool chat app</p>
        <div class="signup-form-box">
            <p>Create Your Account</p>
            <form id="signup-form-form" action="${pageContext.request.contextPath}/register" method="post">
                <div class="signup-form-box-content">
                    <label for="user"></label>
                    <input type="text" name="username" placeholder="Username"
                           class="submit-input" id="user"
                           autocomplete="username" required/>
                    <br/>
                    <label for="password"></label>
                    <input type="password" name="password" placeholder="Password"
                           class="submit-input" id="password"
                           autocomplete="new-password" required/>
                    <label for="name"></label>
                    <input type="text" name="name" placeholder="Name"
                           class="submit-input" id="name"
                           autocomplete="text" required/>
                    <label for="gender"></label>
                    <select name="gender" id="gender" class="gender-select">
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                        <option value="other">Other</option>
                    </select>
                </div>
                <button type="submit" class="submit-form" id="submit-button">
                    Submit
                </button>
            </form>
        </div>
        <p class="signup-p">Have an account
            <mark><a href="${pageContext.request.contextPath}/login" class="signup-link">Login in</a></mark>
            Now!
        </p>
    </div>
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