<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" import = "com.psugv.capstone.login.model.UserModel"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chat | BLURB</title>
    <!--<link rel="stylesheet" href="<c:url value='/css/min-theme.css' />" media="(width < 750px)" />-->
    <link rel="stylesheet" href="<c:url value='/css/max-theme.css' />" <!--media="(width >= 750px)"--> />

    <link rel="stylesheet" href="<c:url value='/css/chat.css' />">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Anta&display=swap" rel="stylesheet">

    <script defer src="<c:url value='/images/logo.svg' />"></script>
</head>

<body>
<nav>
    <div class="nav-content">
        <div class="nav-img">
            <img src="<c:url value='/images/logo.svg' />"  alt="Logo"/>
            <span class="random">BLURB</span>
        </div>
        <div class="nav-login">
            <a href="<c:url value='/logout' />"><button class="nav-login-login" id="toLogin">Logout</button></a>
        </div>
    </div>
</nav>
    
    <main class="current-chat">
        <div class="chat-row">
            <div class="chat-message">
                <p class="chat-timestamp">Date: 09/06/2024</p>
                <p class="chat-sender">Username : Dann123</p>
                <p class="chat-message-data"> What's up dude</p>
            </div>
        </div>
        <div class="chat-row">
            <div class="chat-message-response">
                <p class="chat-timestamp-response">Date: 09/06/2024</p>
                <p class="chat-sender-response">Username : Dann123</p>
                <br>
                <p class="chat-message-data-response"> Whats up dude</p>
            </div>
        </div>
        <div class="chat-row"></div>
            <div class="chat-message">
                <p class="chat-timestamp">Date: 09/06/2024</p>
                <p class="chat-sender">Username : Dann123</p>
                <p class="chat-message-data"> Whats up dude</p>
            </div>
        </div>

        <textarea class="chat-send"></textarea>
        <img src="<c:url value='/images/send.svg' />" class="chat-send-icon">
    </main>


    <footer>
        <div class="footer-content">
            <div class="footer-image">
                <img src="<c:url value='/images/logo.svg' />" alt="Logo" />
                <span class="random">BLURB</span>
            </div>
            <p class="footer-copyright">Copyright of Cool Dudes &copy;2024. All Rights Reserved.</p>
        </div>
    </footer>
</body>

</html>