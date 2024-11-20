// sends message by using post request with message:message and room:chat_id
async function sendMessage() {
    const message = document.getElementById('chat-send').value;
    document.getElementById('chat-send').value = "";

    const chat_id = sessionStorage.getItem("chat_id");
    // Simple way to store the current chat the user is in

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
            chat_room_sender.innerHTML = sessionStorage.getItem('name').toString();
            chat_room_sender.className = "chat-message-sent";

            // For message Data
            let chat_room_content = document.createElement('p');
            chat_room_content.innerHTML = message;
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


            current_chat.appendChild(chat_row);

        } catch (error) {
            console.error(error.message);
        }
    }
}

async function getMessages(chatroom) {
    try {
        const response = await fetch('/Capstone/select', {
            method: 'GET',
            headers: {"chatRoomID": chatroom.toString()}
        });
        if (!response.ok) {
            console.log("ERROR: " + response.status);
            return;
        }
        console.log("response: " + response.toString);
        const json = await response.json();

        let messages = [];

        console.log("typeof result is: " + typeof json);

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

function displayMessages(messages) {
    const current_chat = document.getElementById("current-chat");

    // Removes all old messages in current-chat
    while (current_chat.firstChild) {
        current_chat.removeChild(current_chat.lastChild);
    }

    // creates button to add another user to chat
    const add_user_to_chat = document.createElement('button');
    add_user_to_chat.id = "add_user_to_chat";
    add_user_to_chat.innerHTML = "+";
    add_user_to_chat.title = "Add Another User to your Chat";
    current_chat.appendChild(add_user_to_chat);

    add_user_to_chat.addEventListener("click", ()=> {
        // RUNS FUNCTION TO ADD USER TO CHATROOM
        const chat_id = sessionStorage.getItem("chat_id");
        if(chat_id !== null){
            const add_user_input = document.createElement('div');
            add_user_input.className = "addUserToExistingChat";
            const user_input_title = document.createElement('p');
            user_input_title.innerHTML = "Add User";
            user_input_title.style.float = "left";
            const cancel_user_input = document.createElement('img');
            cancel_user_input.src = sessionStorage.getItem('path') + "/images/close.svg";
            cancel_user_input.style.float = "right";


            const text_area = document.createElement('textarea');
            text_area.style.resize = 'none';

            const search_user = document.createElement('p');
            search_user.id = "search_user";

            search_user.appendChild(user_input_title);
            search_user.appendChild(cancel_user_input);
            search_user.appendChild(text_area);

            add_user_input.appendChild(search_user);
            add_user_to_chat.insertAdjacentElement("afterend",add_user_input);
            //add_user_to_chat(chat_id,);
        }
    });

    if (messages !== 0) {
        for (let i = messages.length - 1; i >= 0; i--) {
            // For Message time
            let chat_room_time = document.createElement('p');
            chat_room_time.className = "chat-timestamp-sent";
            chat_room_time.innerHTML = messages[i][3].toString();

            // For Message sender
            let chat_room_sender = document.createElement('p');
            chat_room_sender.innerHTML = messages[i][2];
            chat_room_sender.className = "chat-message-sent";

            // For message Data
            let chat_room_content = document.createElement('p');
            chat_room_content.innerHTML = messages[i][1];
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

            if (messages[i][2].toString() !== sessionStorage.getItem('name').toString()) {
                chat_room_time.className = "chat-timestamp-received";
                chat_room_sender.className = "chat-message-received";
                chat_room_content.className = "chat-message-data-received";
                chat_div.className = "chat-received";
            }

            current_chat.appendChild(chat_row);
        }
    }

}