<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chat | BLURB</title>
    <link rel="stylesheet" href="<c:url value='/css/theme.css' />" />
    <link rel="stylesheet" href="<c:url value='/css/chat.css' />"/>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Anta&display=swap" rel="stylesheet">

    <script src="<c:url value='/javascript/min-theme.js' />"></script>
    <script defer src="<c:url value='/javascript/chat.js' />"></script>

</head>

<body>

<nav>
    <div class="nav-content">
        <a href="<c:url value='/index' />">
            <div class="nav-img">
                <img src="<c:url value='/images/logo.svg' />"  alt="Logo"/>
                <span class="nav-logo">BLURB</span>
            </div>
            <div class="nav-login">
                <a href="<c:url value='/logout' />">
                    <button class="nav-login-login" id="toLogin">Logout</button>
                </a>
            </div>
        </a>
    </div>
</nav>

<div class="connections">
    <div class="friends">
        <p>Friends</p>
        <ul class="friend-list"></ul>
    </div>
    <div class="channels">
        <p>Channels</p>
        <ul class="channel-list"></ul>
    </div>
</div>

<div class="current-chat">
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
    <div class="chat-row">
        <div class="chat-message">
            <p class="chat-timestamp">Date: 09/06/2024</p>
            <p class="chat-sender">Username : Dann123</p>
            <p class="chat-message-data"> Whats up dude</p>
        </div>
    </div>
    <label>
        <textarea class="chat-send"></textarea>
    </label>
    <img src="<c:url value='/images/send.svg' />" class="chat-send-icon" alt="Send">
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