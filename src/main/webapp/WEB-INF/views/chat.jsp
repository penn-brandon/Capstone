<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" import = "com.psugv.capstone.login.model.UserModel"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chat | BLURB</title>
    <link rel="stylesheet" href="../../../resources/static/css/max-theme.css" media="(width >= 750px)" />
    <link rel="stylesheet" href="../../../resources/static/css/min-theme.css" media="(width < 750px)" />

    <link rel="stylesheet" href="../../../resources/static/css/chat.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Anta&display=swap" rel="stylesheet">

    <script defer src="../../../resources/static/javascript/min-theme.js"></script>
</head>

<body>
    <nav>
        <div class="nav-content">
            <div class="nav-img">
                <img src="../../../resources/static/images/logo.svg" />
                <span class="random">BLURB</span>
            </div>
            <div class="nav-login">
                <button class="nav-login-login">Logout</button>
            </div>
        </div>
    </nav>
    
    <main class="current-chat">
        <div class="chat-row">
            <div class="chat-message">
                <p class="chat-timestamp">Date: 09/06/2024</p>
                <p class="chat-sender">Username : Dann123</p>
                <p class="chat-message-data"> Whats up dude</p>
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
        <img src="../../../resources/static/images/send.svg" class="chat-send-icon">
    </main>


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