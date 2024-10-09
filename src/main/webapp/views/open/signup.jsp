<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Page</title>

    <link rel="stylesheet" href="<c:url value='/css/theme.css' />" />

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Anta&display=swap" rel="stylesheet">

    <script src="<c:url value='/javascript/min-theme.js' />"></script>
    <script src="<c:url value='/javascript/signup.js' />"></script>
</head>

<body>
    <nav>
        <div class="nav-content">
            <a href="<c:url value='/index' />">
                <div class="nav-img">
                    <img src="<c:url value='/images/logo.svg' />"  alt="Logo"/>
                    <span class="nav-logo">BLURB</span>
                </div>
            </a>
        </div>
    </nav>
<div class="signup-form">
    <h1>Get Started Today</h1>
    <p>This is a cool chat app</p>
    <div class="signup-form-box">
        <p>Create Your Account Here</p>
        <form id="signup-form-form">
            <div class="signup-form-box-content">
                <label for="user"></label>
                <input type="text" name="username" placeholder="Username here"
                       class="submit-input" id="user"
                       autocomplete="username" required/>
                <br/>
                <label for="pass"></label>
                <input type="password" name="password" placeholder="Password here"
                       class="submit-input" id="pass"
                       autocomplete="new-password" required/>
            </div>
            <button class="submit-form" id="submit-button">
                Submit
            </button>
        </form>
    </div>
    <p>Have an account <a href="${pageContext.request.contextPath}/login">Login in</a> Now!</p>
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