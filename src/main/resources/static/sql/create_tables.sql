use capstone;

create table authorities
(
    authority_id  int auto_increment primary key not null,
    user_id       int          not null,
    authorityName varchar(255) not null
);

create table user
(
    user_id          int auto_increment primary key not null,
    name             varchar(225) not null,
    gender           varchar(6)   not null,
    username         varchar(255) not null,
    password         varchar(255) not null,
    date_of_creation date         not null,
    authority_id     int          not null,
    is_Enable        bool         not null,
    FOREIGN KEY (authority_id) REFERENCES authorities (authority_id)
);

CREATE TABLE ChatRoom (
    chat_room_id INT AUTO_INCREMENT PRIMARY KEY,
    joinable TINYINT(1) NOT NULL DEFAULT 0
);

