// loads all chat rooms that a given user is a part of - helper function to displayChatRooms()
async function getChatRooms() {
    try {
        const response = await fetch('/Capstone/loadAllChatRoomName', {method: 'GET'});
        if (!response.ok) {
            console.log("ERROR: " + response.status);
        }
        const json = await response.json();
        let chat_rooms = [];

        for (let i = 0; i < json.length; i++) {
            let curChatRoom = [];
            chatroomSet.add(Object.values(json)[i][1]);
            curChatRoom.push(Object.values(json)[i][1]); // id
            curChatRoom.push(Object.values(json)[i][3]); // name
            let date = Object.values(json)[i][4] // date
            curChatRoom.push(new Date(date).getDay() + "/" + new Date(date).getMonth()); //date
            chat_rooms.push(curChatRoom);
        }
        return chat_rooms;
    } catch (error) {
        console.error(error.message);
    }
}


// displays all the chat rooms visually on the page
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
            // chat_rooms[i][0] is the chat room id
            new_chat.addEventListener('click', async () => {
                let messages = await getMessages(chat_rooms[i][0]);
                displayMessages(messages);
                sessionStorage.setItem("chat_id",chat_rooms[i][0]);
            });

            chat_room_div.appendChild(new_chat);
        }
    }
    else {
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

    // Creates Search User button at the end of channels list

    let user_search = document.createElement('button');
    user_search.id = "chat-room-plus";
    user_search.innerHTML = '\u{2315}';
    user_search.style.fontSize = "30px";
    user_search.title = "Search for Users to Create a New Chatroom With";

    chat_room_div.append(user_search);

    user_search.addEventListener('click', async () => {
        let chatRoomName = getUsernameFromUser();
    });

}

// This creates a text input field for the user to input new chat room name
function getUsernameFromUser() {
    let username = null;
    if (document.getElementById("search_username") == null){
        const chat_room_div = document.getElementById("channels-list");

        const cancel_username = document.createElement('img');
        cancel_username.src = "${pageContext.request.contextPath}/images/close.svg";
        cancel_username.style.float = "right";

        const search_user_title = document.createElement('p');
        search_user_title.textContent = "Search Users"

        const text_area = document.createElement('textarea');
        text_area.style.resize = 'none';

        const new_username = document.createElement('p');
        new_username.id = "search_username";

        new_username.appendChild(cancel_username);
        new_username.appendChild(search_user_title);
        new_username.appendChild(text_area);

        chat_room_div.appendChild(new_username);

        cancel_username.addEventListener('click', () => {
            chat_room_div.removeChild(new_username);
            if (document.getElementById(("search-user-div"))){
                document.getElementById("search-user-div").remove();
            }
        });

        text_area.addEventListener("input", async () => {
            if (document.getElementById("search-user-div")){
                document.getElementById("search-user-div").remove();
            }
            username = text_area.value;
            if (username !== null && username.length > 2){
                let username_list = await searchUser(username);

                const user_div = document.createElement('div');
                user_div.id = "search-user-div";

                chat_room_div.append(user_div);
                // shows the current queried results
                for (let i = 0; i < username_list.length; i++) {
                    if (username_list[i][0].toString() === sessionStorage.getItem('username').toString()){
                        continue;
                    }
                    const user_p = document.createElement('p');
                    user_p.innerHTML = username_list[i][0];
                    user_p.className = 'searched_username';
                    user_div.append(user_p);

                    user_p.addEventListener('click', async () => {
                        await createNewChatRoom(username_list[i]);
                    })
                }
            }
        });
    }
}

async function createNewChatRoom(searched_user){
    const response = await fetch('/Capstone/createNewChatRoom', {
        headers:{"Content-Type":"application/json"},
        method:'POST',
        body: JSON.stringify({
            "username": searched_user[0].toString(),
            "id": searched_user[2].toString(),
            "name": searched_user[1].toString()})

    });
    if (!response.ok) {
        console.log("ERROR: " + response.status);
    }
    let chat_room_name = await response.json();
    console.log(chat_room_name);

    const chatroom = chat_room_name["chatRoom"]["id"];

    let chat_rooms = await getChatRooms();
    await displayChatRooms(chat_rooms);

    let messages = await getMessages(chatroom);
    console.log("Message length: " + messages.length)
    if(messages.length !== 0){

        displayMessages(messages);
    }
    chat_id = chatroom;

    let send_message_enter = document.getElementById('chat-send');
    send_message_enter.addEventListener("keypress", function (event) {
        if (event.key === "Enter") {
            event.preventDefault();
            document.getElementById('send-button').click();
        }
    });
}

// first need to search for user then with their username send to create new chatroom
async function searchUser(input) {
    const response = await fetch('/Capstone/searchUsers', {
        method: 'POST',
        headers: {"username": input.toString()}
    });

    if (!response.ok) {
        console.log("ERROR: " + response[1]);
        return;
    }
    const json = JSON.parse(await response.text());
    let usernames = [];
    for (let i = 0; i < json.length; i++) {
        let curUsername = [];
        curUsername.push(json[i].username);
        curUsername.push(json[i].name);
        curUsername.push(json[i].id);
        usernames.push(curUsername);

    }
    return usernames;
}

async function addUserToChatRoom(chat_room_id, username, user_id, name) {

    const response = await fetch('/Capstone/addUserToChatRoom', {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
            "chatroom": chat_room_id.toString(),
            "id": user_id.toString()
        }
    });
    if (!response.ok) {
        console.log("ERROR: " + response.status);
    }
}

