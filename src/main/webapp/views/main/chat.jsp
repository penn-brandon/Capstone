<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import = "com.psugv.capstone.login.model.UserModel"
         import = "com.psugv.capstone.chat.model.ChatRoom"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Chat | BLURB</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chat.css"/>

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Anta&display=swap" rel="stylesheet">

        <script defer src="${pageContext.request.contextPath}/javascript/theme.js"></script>
        <script defer src="${pageContext.request.contextPath}/javascript/chat.js"></script>

        <script defer>
            window.onload = async () => {
                let chat_rooms = await getChatRooms();
                await displayChatRooms(chat_rooms);
                let chat_id = null;

                let send_message_enter = document.getElementById('chat-send');
                send_message_enter.addEventListener("keypress", function(event) {
                    if (event.key === "Enter"){
                        event.preventDefault();
                        document.getElementById('send-button').click();
                    }
                });
            }

            function startListener() {

                var socket = new SockJS('/capstone');

                var stompClient = Stomp.over(socket);

                let userName = `${sessionScope.userModel.getUsername()}`;

                stompClient.connect({}, function(frame) {

                    console.log('Connected: ' + frame);

                    console.log("listening to /listening/" + userName);

                    stompClient.subscribe('/listening/' + userName, function(message) {

                        var updatesDiv = document.getElementById('updates');

                        updatesDiv.innerHTML += message.body + '<br/>';
                    });
                });
            }


            async function sendMessage() {
                const message = document.getElementById('chat-send').value;
                document.getElementById('chat-send').value = "";

                if (chat_id !== null) {
                    try {
                        const response = await fetch('/Capstone/send', {
                            method: 'POST',
                            headers: {"Content-Type": "application/json"},
                            body: JSON.stringify({
                                "message": message,
                                "room": chat_id
                            })
                        });
                        if (!response.ok) {
                            console.log("ERROR: " + response.status);
                        }

                        let messages = await getMessages(chat_id);
                        displayMessages(messages);

                    } catch (error) {
                        console.error(error.message);
                    }
                }
            }

            async function getChatRooms() {
                try {
                    const response = await fetch('/Capstone/loadAllChatRoomName', {method: 'GET'});
                    if (!response.ok) {
                        console.log("ERROR: "+  response.status);
                    }
                    const json = await response.json();
                    let chat_rooms = [];

                    for (let i = 0; i < json.length; i++) {
                        let curChatRoom = [];
                        curChatRoom.push(Object.values(json)[0][0]); // id
                        curChatRoom.push(Object.values(json)[0][3]); // name
                        let date = Object.values(json)[0][4] // date
                        curChatRoom.push(new Date(date).getDay() + "/" + new Date(date).getMonth()); //date
                        chat_rooms.push(curChatRoom);
                    }
                    return chat_rooms;
                } catch (error) {
                    console.error(error.message);
                }
            }

            async function displayChatRooms(chat_rooms) {
                const chat_room_div = document.getElementById("channels-list");

                // Removes all extra p tags from chat room list
                while (chat_room_div.firstChild) {
                    chat_room_div.removeChild(chat_room_div.lastChild);
                }

                if (chat_rooms.length > 0) {
                    for (let i = 0; i < chat_rooms.length; i++) {
                        let chat_room_name = document.createElement('span');
                        chat_room_name.textContent = chat_rooms[i][1]; //NAME

                        let lastModifiedDate = document.createElement('span');
                        lastModifiedDate.textContent = chat_rooms[i][2]; //DATE

                        let new_chat = document.createElement('p');
                        new_chat.appendChild(chat_room_name);
                        new_chat.appendChild(lastModifiedDate);


                        // Loads new Messages when you click the channel name
                        new_chat.addEventListener('click', async () => {
                            let messages = await getMessages(chat_rooms[i][0]);
                            displayMessages(messages);
                            chat_id = chat_rooms[i][0];
                        });

                        chat_room_div.appendChild(new_chat);
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

                    chat_room_div.appendChild(new_chat);
                }

            }

            async function getMessages(chatroom){
                const current_chat = document.getElementById("current-chat");

                try {
                    const response = await fetch('/Capstone/select', {
                        method: 'GET',
                        headers:{"Content-Type":"application/json", "chatRoomID":chatroom.toString()}
                    });
                    if (!response.ok) {
                        console.log("ERROR: "+  response.status);
                    }
                    const json = await response.json();
                    let messages = [];

                    for (let i = 0; i < json.length; i++) {
                        let curMessage = [];
                        curMessage.push(json[i].id);
                        curMessage.push(json[i].content);
                        curMessage.push(json[i].sender);
                        curMessage.push(json[i].time);
                        messages.push(curMessage);
                    }
                    return messages;
                } catch (error) {
                    console.error(error.message);
                }
            }


            // INDEX LIST
            // ID = 0, CONTENT = 1, SENDER = 2, TIME = 3

            // <div>
            //   <div>
            //     <p>

            function displayMessages(messages){
                const current_chat = document.getElementById("current-chat");

                // Removes all old messages in current-chat
                while (current_chat.firstChild) {
                    current_chat.removeChild(current_chat.lastChild);
                }

                if (messages !== 0) {
                    for (let i = messages.length-1; i >= 0; i--) {
                        // For Message time
                        let chat_room_time = document.createElement('p');
                        chat_room_time.className = "chat-timestamp";
                        chat_room_time.innerHTML = messages[i][3].toString();

                        // For Message sender
                        let chat_room_sender = document.createElement('p');
                        chat_room_sender.innerHTML = messages[i][2];
                        chat_room_sender.className = "chat-sender";

                        // For message Data
                        let chat_room_content = document.createElement('p');
                        chat_room_content.innerHTML = messages[i][1];
                        chat_room_content.className = "chat-message-data";

                        // Smaller div tag creation
                        let chat_div = document.createElement('div');
                        chat_div.className = "chat-message";
                        chat_div.appendChild(chat_room_time);
                        chat_div.appendChild(chat_room_sender);
                        chat_div.appendChild(chat_room_content);

                        let chat_row = document.createElement('div');
                        chat_row.className = "chat-row";
                        chat_row.appendChild(chat_div);

                        if(messages[i][0].toString() !== `${sessionScope.userModel.getId()}`.toString()){
                            chat_room_time.className = "chat-timestamp-response";
                            chat_room_sender.className = "chat-sender-response";
                            chat_room_content.className = "chat-message-data-response";
                            chat_div.className  = "chat-message-response";
                        }

                        current_chat.appendChild(chat_row);
                    }
                }

            }


        </script>

    </head>

    <body>
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
            <p>Channels</p>
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
            <button id="send-button" onclick="sendMessage()" >
                <img src="${pageContext.request.contextPath}/images/send.svg" class="chat-send-icon" alt="Send">
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