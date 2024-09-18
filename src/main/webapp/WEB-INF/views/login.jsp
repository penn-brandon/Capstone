<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign In</title>
    <link rel="stylesheet" href="../../../resources/static/css/theme.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Anta&display=swap" rel="stylesheet">


    <script src="../../../resources/static/js/jquery-3.6.0.min.js"></script>

    <script>
        //This uis the java code!!
    </script>
</head>

<body>
    <nav>
        <div class="nav-content">
            <div class="nav-img">
                <img src="../../../resources/static/images/logo.svg" />
                <span class="random">BLURB</span>
            </div>
            <div class="nav-login">
                <a href="login.html"><button class="nav-login-login">Login</button></a>
                <a href="signup.jsp"><button class="nav-login-register">Register</button></a>
            </div>
        </div>
    </nav>
    <div class="signup-form">
        <h1>Coming back so soon!</h1>
        <p>Thanks for choosing BLURB.</p>
        <div class="signup-form-box">
            <p>Login Here</p>
            <form>
                <div class="signup-form-box-content"></div>
                <input type="text" name="username" placeholder="Username here" class="submit-input" id="user"
                    autocomplete="username" required />
                <br>
                <input type="password" name="password" placeholder="Password here" class="submit-input" id="pass"
                    autocomplete="current-password" required />
                <br>
                <input type="submit" value="Submit" class="submit-form" />
            </form>
        </div>

        <p class="signup-form-no-account">No account? <a href="signup.jsp">Sign up</a></p>
    </div>
    
    </div>


    <footer>
        <div class="footer-content">
            <div class="footer-image">
                <img src="../../../resources/static/images/logo.svg" alt="Logo">
                <span class="random">BLURB</span>
            </div>
            <p class="footer-copyright">Copyright of Cool Dudes &copy;2024. All Rights Reserved.</p>
        </div>
    </footer>
</body>

</html>