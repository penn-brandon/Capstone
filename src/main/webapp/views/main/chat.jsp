<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chat | BLURB</title>
    <link rel="stylesheet" href="<c:url value='/css/theme.css' />"/>
    <link rel="stylesheet" href="<c:url value='/css/chat.css' />"/>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Anta&display=swap" rel="stylesheet">

    <script defer src="<c:url value='/javascript/min-theme.js' />"></script>
    <script defer src="<c:url value='/javascript/chat.js' />"></script>

    <script defer>
        window.onload = () => {
            getChatRooms()
        }

        async function sendMessage() {
            const message = document.getElementById('chat-send').value;
            const chatroom = document.getElementById('TODO').value;

            fetch('/getMessage', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'

                },
                body: JSON.stringify({
                    chatRoom: chatroom,
                    message: message
                })
            })
                .then(response => response.text())
                .then(data => console.log(data))
                .catch(error => console.error('ERROR:', error));
        }

        async function getChatRooms() {
            const channel_name = document.getElementById("channels-list");

            const url = '/Capstone/loadAllChatRoomName';
            try {
                const response = await fetch(url, {method: 'GET'});
                if (!response.ok) {
                    console.log("ERROR: "+  response.status);
                }
                const json = await response.json();

                console.log(json);

                return json;
            } catch (error) {
                console.error(error.message);
            }
        }
    </script>

</head>

<body>
<nav>
    <div class="nav-content">
        <div class="nav-img">
            <img src="<c:url value='/images/logo.svg' />" alt="Logo"/>
            <span class="nav-logo">BLURB</span>
        </div>
        <div class="profile-div">
            <button class="profile" id="profile" onclick="profile_click()">
                <img src="<c:url value='/images/user.svg' />" alt="Profile"/>
            </button>
            <div class="profile-dropdown" id="profile-dropdown">
                <a href="${pageContext.request.contextPath}/profile" id="profile-link">Profile</a>
                <a href="${pageContext.request.contextPath}/logout" id="logout-link">Logout</a>
            </div>
        </div>
    </div>
</nav>

<div class="connections-nav">
    <div class="connections">
        <div class="friends">
            <div class="friends-button" id="friends-button" onclick="friends_click('<c:url value='/images/'/>')">
                <p>Friends</p>
                <img id="friends-drop" src="<c:url value='/images/chevron-down.svg' />" alt="Logo"/>
            </div>
            <div class="friends-list" id="friends-list">
                <p>Default Friend 1</p>
                <p>Default Friend 2</p>
                <p>Default Friend 3</p>
            </div>
        </div>
        <div class="channels">
            <div class="channels-button" id="channels-button" onclick="channels_click('<c:url value='/images/'/>')">
                <p>Channels</p>
                <img id="channels-drop" src="<c:url value='/images/chevron-down.svg' />" alt="Logo"/>
            </div>
            <div class="channels-list" id="channels-list">
                <p>Default Channel 1</p>
                <p>Default Channel 2</p>
                <p>Default Channel 3</p>
            </div>
        </div>
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
        <textarea class="chat-send" id="chat-send"></textarea>
    </label>
    <button id="send-button" onclick="sendMessage()">
        <img src="<c:url value='/images/send.svg' />" class="chat-send-icon" alt="Send">
    </button>

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