use
capstone;

create table user
(
    user_id          int auto_increment primary key not null,
    name             varchar(225) not null,
    gender           varchar(6)   not null,
    username         varchar(255) not null unique,
    password         varchar(255) not null,
    date_of_creation datetime     not null default CURRENT_TIMESTAMP,
    is_Enable        bool         not null default true
);

create table authorities
(
    authority_id  int auto_increment primary key not null,
    user_id       int          not null,
    authorityName varchar(255) not null,
    FOREIGN KEY (user_id) REFERENCES user (user_id)
);

CREATE TABLE ChatRoom
(
    chat_room_id int auto_increment primary key not null,
    joinable     Bool NOT NULL
);

CREATE TABLE ChatRoomToUser
(
    id           int auto_increment primary key not null,
    user_id      int not null,
    chat_room_id int not null,
    FOREIGN KEY (user_id) REFERENCES user (user_id),
    FOREIGN KEY (chat_room_id) REFERENCES ChatRoom (chat_room_id)
);
