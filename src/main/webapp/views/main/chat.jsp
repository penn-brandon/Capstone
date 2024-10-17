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
            let chat_rooms = getChatRooms();
            displayChatRooms(chat_rooms);
        }

        async function sendMessage() {
            const message = document.getElementById('chat-send').value;
            console.log(message);
            const chatroom = document.getElementById('TODO').value;
            console.log(chatroom);

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
            try {
                const response = await fetch('/Capstone/loadAllChatRoomName', {method: 'GET'});
                if (!response.ok) {
                    console.log("ERROR: "+  response.status);
                }
                const json = await response.json();
                console.log(json);
                let chat_rooms = [];

                for (let i = 0; i < json.length; i++) {
                    let curChatRoom = [];
                    curChatRoom += json[i].chatRoomName;
                    curChatRoom += json[i].chatRoomId;
                    curChatRoom +=json[i].lastModifiedDate;
                    chat_rooms += curChatRoom;
                }

                return chat_rooms;
            } catch (error) {
                console.error(error.message);
            }
        }

        function displayChatRooms(chats) {
            const channel_name = document.getElementById("channels-list");

            // Removes all extra p tags from channel list
            while (channel_name.firstChild) {
                channel_name.removeChild(channel_name.lastChild);
            }

            if (chats.length > 0) {
                for (let i = 0; i < chats.length; i++) {
                    let chat_room_name = document.createElement('span');
                    chat_room_name.textContent = chats[i][0];

                    let chat_room_id = document.createElement('span');
                    chat_room_id.textContent = chats[i][1];

                    let lastModifiedDate = document.createElement('span');
                    lastModifiedDate.textContent = chats[i][2];

                    let new_chat = document.createElement('p');
                    new_chat.appendChild(chat_room_name);
                    new_chat.appendChild(chat_room_id);
                    new_chat.appendChild(lastModifiedDate);

                    channel_name.appendChild(new_chat);
                }
            } else {
                let chat_room_name = document.createElement('span');
                chat_room_name.textContent = "None";

                let chat_room_id = document.createElement('span');
                chat_room_id.textContent = "None";

                let lastModifiedDate = document.createElement('span');
                lastModifiedDate.textContent = "None";

                let new_chat = document.createElement('p');
                new_chat.appendChild(chat_room_name);
                new_chat.appendChild(chat_room_id);
                new_chat.appendChild(lastModifiedDate);

                channel_name.appendChild(new_chat);
            }

        }

        async function getMessages(){
            const current_chat = document.getElementById("current-chat");

            try {
                const response = await fetch('/Capstone/loadMessage', {method: 'GET'});
                if (!response.ok) {
                    console.log("ERROR: "+  response.status);
                }
                const json = await response.json();
                console.log(json);

                let messages = [];

                for (let i = 0; i < json.length; i++) {
                    let curMessage = [];
                    curMessage += json[i].timestamp;
                    curMessage += json[i].username;
                    curMessage +=json[i].data;
                    messages += curMessage;
                }
                return messages;
            } catch (error) {
                console.error(error.message);
            }
        }

        function displayMessages(messages){
            const current_chat = document.getElementById("current-chat");

            // Removes all extra p tags from channel list
            while (current_chat.firstChild) {
                current_chat.removeChild(current_chat.lastChild);
            }

            if (messages.length > 0) {
                for (let i = 0; i < messages.length; i++) {
                    let chat_room_name = document.createElement('span');
                    chat_room_name.textContent = messages[i][0];

                    let chat_room_id = document.createElement('span');
                    chat_room_id.textContent = messages[i][1];

                    let lastModifiedDate = document.createElement('span');
                    lastModifiedDate.textContent = messages[i][2];

                    let new_chat = document.createElement('p');
                    new_chat.appendChild(chat_room_name);
                    new_chat.appendChild(chat_room_id);
                    new_chat.appendChild(lastModifiedDate);

                    current_chat.appendChild(new_chat);
                }
            } else {
                let chat_room_name = document.createElement('span');
                chat_room_name.textContent = "None";

                let chat_room_id = document.createElement('span');
                chat_room_id.textContent = "None";

                let lastModifiedDate = document.createElement('span');
                lastModifiedDate.textContent = "None";

                let new_chat = document.createElement('p');
                new_chat.appendChild(chat_room_name);
                new_chat.appendChild(chat_room_id);
                new_chat.appendChild(lastModifiedDate);

                current_chat.appendChild(new_chat);
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
                <p>Default Friend </p>
            </div>
        </div>
        <div class="channels">
            <div class="channels-button" id="channels-button" onclick="channels_click('<c:url value='/images/'/>')">
                <p>Channels</p>
                <img id="channels-drop" src="<c:url value='/images/chevron-down.svg' />" alt="Logo"/>
            </div>
            <div class="channels-list" id="channels-list">
                <p>Default Channel</p>
            </div>
        </div>
    </div>
</div>


<div class="current-chat" id="current-chat">
    <!-- This is default example and not actual chat -->
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
    <!-- End of Example-->
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