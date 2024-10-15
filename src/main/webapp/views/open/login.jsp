<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign In</title>

    <link rel="stylesheet" href="<c:url value='/css/theme.css' />" />

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Anta&display=swap" rel="stylesheet">

    <script defer src="<c:url value='/javascript/min-theme.js' />"></script>
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
<div class="main">
    <div class="signup-form">
        <h1>Coming back so soon!</h1>
        <p>Thanks for choosing BLURB.</p>
        <div class="signup-form-box">
            <p>Login Here</p>
            <form method="post" action="<c:url value='/login'/>">
                <div class="signup-form-box-content"></div>
                <input type="text" name="username" placeholder="Username here" class="submit-input" id="username"
                       autocomplete="username" required/>
                <br>
                <input type="password" name="password" placeholder="Password here" class="submit-input" id="password"
                       autocomplete="current-password" required/>
                <br>
                <input type="submit" value="Submit" class="submit-form" id="submit_button"/>
            </form>
        </div>
        <p>Don't have a account <a href="${pageContext.request.contextPath}/signup">Sign up</a> Now!</p>
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