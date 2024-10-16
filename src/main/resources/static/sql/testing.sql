use capstone;

drop table IF EXISTS chatroomname;

CREATE TABLE IF NOT EXISTS ChatRoomName(
    chat_room_name_id INT PRIMARY KEY,
    user_id INT,
    ChatRoom_id INT NOT NULL,
    admin BOOLEAN NOT NULL,
    chat_room_name TEXT NOT NULL,
    last_modified DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES User(user_id),
    FOREIGN KEY (ChatRoom_id) REFERENCES ChatRoom(id)
);


insert into chatroomname (user_id,ChatRoom, admin, chat_room_name, last_motified) values (1,1,False,"Hello People",NOW() );
insert into chatroomname (user_id,ChatRoom, admin, chat_room_name, last_motified) values (1,2,False,"Discover Knime",NOW() );