<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chat | BLURB</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chat.css"/>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Anta&display=swap" rel="stylesheet">

    <script defer src="${pageContext.request.contextPath}/javascript/chat.js"></script>

    <script src="${pageContext.request.contextPath}/javascript/sockjs.min.js"></script>
    <script src="${pageContext.request.contextPath}/javascript/stomp.min.js"></script>

    <script defer src="${pageContext.request.contextPath}/javascript/message.js"></script>
    <script defer src="${pageContext.request.contextPath}/javascript/chatroom.js"></script>
    <script defer>
        const chatroomSet = new Set();
        let chat_id = null;
        window.onload = async () => {
            let chat_rooms = await getChatRooms();
            await displayChatRooms(chat_rooms);
            sessionStorage.setItem("chat_id", null);
            sessionStorage.setItem("name", `${sessionScope.userModel.getName()}`);
            sessionStorage.setItem("username", `${sessionScope.userModel.getUsername()}`);
            sessionStorage.setItem("path", `${pageContext.request.contextPath}`);

            let send_message_enter = document.getElementById('chat-send');
            send_message_enter.addEventListener("keypress", function (event) {
                if (event.key === "Enter") {
                    event.preventDefault();
                    document.getElementById('send-button').click();
                }
            });
            startListener();
            const refresh_icon_img = document.getElementById('chats-refresh-icon-img')
            const refresh_icon = document.getElementById('chats-refresh-icon');
            refresh_icon.addEventListener('click', async () => {
                window.onload();
                location.reload();
                refresh_icon_img.animate([{transform: "rotate(0deg)"}, {transform: "rotate(180deg)"}, {transform: "rotate(360deg)"}], {
                    duration: 1000,
                    iterations: 1
                });
            });

        }

        function startListener() {

            var socket = new SockJS('http://217.15.171.16/Capstone/capstone');
            //var socket = new SockJS('/Capstone/capstone');

            var stompClient = Stomp.over(socket);

            let userName = `${sessionScope.userModel.getUsername()}`;

            stompClient.connect({}, function (frame) {
                stompClient.subscribe("/listening/" + userName, function (message) {

                    const resultMap = JSON.parse(message.body);

                    try {
                        const current_chat = document.getElementById("current-chat");

                        let chat_room_time = document.createElement('p');
                        chat_room_time.className = "chat-timestamp-sent";
                        let date = new Date();

                        chat_room_time.innerHTML = (
                            date.getMonth() + 1 + "/" +
                            date.getDate() + " " +
                            date.getHours() + ":" +
                            date.getMinutes()).toString();

                        // For Message sender
                        let chat_room_sender = document.createElement('p');
                        chat_room_sender.innerHTML = resultMap.senderName;
                        chat_room_sender.className = "chat-message-sent";

                        // For message Data
                        let chat_room_content = document.createElement('p');
                        chat_room_content.innerHTML = resultMap.message;
                        chat_room_content.className = "chat-message-data-sent";

                        // Smaller div tag creation
                        let chat_div = document.createElement('div');
                        chat_div.className = "chat-sent";
                        chat_div.appendChild(chat_room_time);
                        chat_div.appendChild(chat_room_sender);
                        chat_div.appendChild(chat_room_content);

                        let chat_row = document.createElement('div');
                        chat_row.className = "chat-row";
                        chat_row.appendChild(chat_div);

                        if (resultMap.senderName.toString() !== `${sessionScope.userModel.getName()}`.toString()) {
                            chat_room_time.className = "chat-timestamp-received";
                            chat_room_sender.className = "chat-message-received";
                            chat_room_content.className = "chat-message-data-received";
                            chat_div.className = "chat-received";
                        }


                        current_chat.appendChild(chat_row);

                    } catch (error) {
                        console.error(error.message);
                    }
                });
            });
        }
    </script>


</head>

<body>
<div class="container">
    <nav>
        <div class="nav-content">
            <div class="nav-img">
                <img src="${pageContext.request.contextPath}/images/logo.svg" alt="Logo"/>
                <span class="nav-logo">BLURB</span>
            </div>
            <div class="profile-div" id="profile-div">
                <button class="profile" id="profile" onclick="profile_click()">
                    <img src="${pageContext.request.contextPath}/images/user.svg" alt="Profile"/>
                </button>
            </div>
        </div>
    </nav>

    <div class="chats">
        <div class="chats-title">
            <p class="chats-title-title">Channels</p>
            <a id="chats-refresh-icon">
                <img id="chats-refresh-icon-img" src="${pageContext.request.contextPath}/images/refresh.svg"
                     alt="Refresh"/>
            </a>
        </div>
        <div class="channels-list" id="channels-list">
            <p>Default Channel</p>
        </div>
    </div>
    <div class="current-chat" id="current-chat">
    </div>
    <div class="chat-box">
        <label>
            <textarea class="chat-send" id="chat-send"></textarea>
        </label>
        <div id="send-button" onclick="sendMessage()">
            <img src="${pageContext.request.contextPath}/images/send.svg" class="chat-send-icon" alt="Send">
        </div>
    </div>

    <footer>
        <div class="footer-div">
            <p class="footer-copyright">
                Copyright of Cool Dudes &copy;2024. All Rights Reserved.
            </p>
        </div>
    </footer>
</div>
</body>

</html>