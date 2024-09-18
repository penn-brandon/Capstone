<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Sign Up</title>
    <link rel="stylesheet" href="../../../resources/static/css/theme.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link href="https://fonts.googleapis.com/css2?family=Anta&display=swap" rel="stylesheet" />
    <script defer src="../../../resources/static/javascript/signup.js"></script>
</head>

<body>
    <nav>
        <div class="nav-content">
            <div class="nav-img">
                <img src="../../../resources/static/images/logo.svg" />
                <span class="random">BLURB</span>
            </div>
            <div class="nav-login">
                <a href="login.jsp"><button class="nav-login-login">Login</button></a>
                <a href="signup.html"><button class="nav-login-register">Register</button></a>
            </div>
        </div>
    </nav>
    <div class="signup-form">
        <h1>Get Started Today</h1>
        <p>This is a cool chat app</p>
        <div class="signup-form-box">
            <p>Create Your Account Here</p>
            <form id="signup-form-form">
                <div class="signup-form-box-content">
                    <input type="username" name="username" placeholder="Username here" class="submit-input" id="user"
                        autocomplete="username" required />
                    <br />
                    <input type="password" name="password" placeholder="Password here" class="submit-input" id="pass"
                        autocomplete="new-password" required />
                </div>
                <button class="submit-form" id="submit-button">
                    Submit
                </button>
            </form>
        </div>
        
    </div>

    <footer>
        <div class="footer-content">
            <div class="footer-image">
                <img src="../../../resources/static/images/logo.svg" alt="Logo" />
                <span class="random">BLURB</span>
            </div>
            <p class="footer-copyright">Copyright of Cool Dudes &copy;2024. All Rights Reserved.</p>
        </div>
    </footer>
</body>

</html>