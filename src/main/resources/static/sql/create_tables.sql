-- script for creating backend mysql database -- 
drop database if exists capstone;
create database capstone;
use capstone;


create table permission
(
    permission_id   int auto_increment primary key not null,
    permission_name VARCHAR(255)
);

create table chat_room
(
    chat_room_id int auto_increment primary key not null,
    can_join     bool default false,
    members      LONGBLOB,
    message_list LONGBLOB
);

create table user
(
    user_id          int auto_increment primary key not null,
    username         VARCHAR(255) not null,
    password         VARCHAR(255) not null,
    date_of_creation datetime default NOW() not null,
    permission       int default 0 not null,
    chatRoomList     LONGBLOB,
    FOREIGN KEY (permission) REFERENCES permission (permission_id)
);

create table chat_room_name
(
    user           int not null,
    chat_room      int not null,
    ownership      bool,
    chat_room_name varchar(255),
    FOREIGN KEY (user) REFERENCES user (user_id),
    FOREIGN KEY (chat_room) REFERENCES chat_room (chat_room_id)
);

create table connections
(
    user       int not null,
    connection int not null,
    block      bool default false not null,
    FOREIGN KEY (user) REFERENCES user (user_id),
    FOREIGN KEY (connection) REFERENCES user (user_id)
);

create table message
(
    message_id int auto_increment primary key not null,
    content    blob not null,
    date       datetime default NOW() not null,
    chatroom   int not null,
    sender     int not null,
    FOREIGN KEY (chatroom) REFERENCES chat_room (chat_room_id),
    FOREIGN KEY (sender) REFERENCES user (user_id)
);