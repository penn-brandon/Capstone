-- creates a new database --
drop database if exists capstone;
create database capstone;
use capstone;


create table permission
(
    permission_id   serial primary key,
    permission_name VARCHAR(255)
);

create table chat_room
(
    chat_room_id serial primary key,
    can_join     bool,
    members      LONGBLOB,
    message_list LONGBLOB
);

create table user
(
    user_id          serial primary key,
    username         VARCHAR(255) not null,
    password         VARCHAR(255) not null,
    date_of_creation datetime default NOW(),
    permission       int,
    chatRoomList     LONGBLOB,
    FOREIGN KEY (permission) REFERENCES permission (permission_id)
);

create table chat_room_name
(
    user           int,
    chat_room      integer,
    ownership      bool,
    chat_room_name varchar(255),
    FOREIGN KEY (user) REFERENCES user (user_id),
    FOREIGN KEY (chat_room) REFERENCES chat_room (chat_room_id)
);

create table connections
(
    user       int,
    connection int,
    block      bool,
    FOREIGN KEY (user) REFERENCES user (user_id),
    FOREIGN KEY (connection) REFERENCES user (user_id)
);

create table message
(
    message_id serial primary key,
    content    blob,
    date       datetime,
    chatroom   int,
    sender     int,
    FOREIGN KEY (chatroom) REFERENCES chat_room (chat_room_id),
    FOREIGN KEY (sender) REFERENCES user (user_id)
);