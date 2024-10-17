use capstone;

CREATE TABLE 1_ChatRoomName(
    chat_room_name_id SERIAL PRIMARY KEY NOT NULL,
    ChatRoom INT NOT NULL,
    admin BOOLEAN NOT NULL,
    chat_room_name TEXT NOT NULL,
    last_modified DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ChatRoom) REFERENCES ChatRoom(chat_room_id)
);


CREATE TABLE 2_ChatRoomName(
    chat_room_name_id SERIAL PRIMARY KEY NOT NULL,
    ChatRoom INT NOT NULL,
    admin BOOLEAN NOT NULL,
    chat_room_name TEXT NOT NULL,
    last_modified DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ChatRoom) REFERENCES ChatRoom(chat_room_id)
);


create table 1_message(
    message_id SERIAL primary key not null,
    time DATETIME not null DEFAULT CURRENT_TIMESTAMP,
    content varchar(225) not null,
    chatroom int not null,
    sender BOOLEAN not null,
    FOREIGN KEY (chatroom) REFERENCES ChatRoom(chat_room_id)
);


create table 2_message(
    message_id SERIAL primary key not null,
    time DATETIME not null DEFAULT CURRENT_TIMESTAMP,
    content varchar(225) not null,
    chatroom int not null,
    sender int not null,
    FOREIGN KEY (chatroom) REFERENCES ChatRoom(chat_room_id)
);
