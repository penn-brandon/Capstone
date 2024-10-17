use capstone;

drop table IF EXISTS chatroomname;

CREATE TABLE IF NOT EXISTS 1_ChatRoomName(
    chat_room_name_id INT PRIMARY KEY,
    user_id INT,
    ChatRoom_id INT NOT NULL,
    admin BOOLEAN NOT NULL,
    chat_room_name TEXT NOT NULL,
    last_modified DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES User(user_id),
    FOREIGN KEY (ChatRoom_id) REFERENCES ChatRoom(id)
);


insert into 1_chatroomname (user_id,ChatRoom, admin, chat_room_name, last_motified) values (1,1,False,"Hello People",NOW() );
insert into 1_chatroomname (user_id,ChatRoom, admin, chat_room_name, last_motified) values (1,2,False,"Discover Knime",NOW() );

create table if not exist 1_message(
    message_id int primary key,
    time DATETIME not null default current timestamp,
    content varchar(max) not null,
    
);