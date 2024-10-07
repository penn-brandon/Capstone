use capstone;

drop table IF EXISTS user;
drop table IF EXISTS authorities;

create table authorities
(
    authority_id int auto_increment primary key not null,
    user_id int not null,
    authorityName varchar(255) not null
);

create table user(
    user_id int auto_increment primary key not null,
    name varchar(225) not null,
    gender varchar(6) not null,
    username varchar(255) not null,
    password varchar(255) not null,
    date_of_creation date not null,
    authority_id int not null,
    is_Enable bool not null,
    FOREIGN KEY (authority_id) REFERENCES authorities (authority_id)
);

insert into authorities (
    user_id,
    authorityName)
values(
          1,
          'NORMAL');

insert into user(
    name,
    gender,
    username,
    password,
    date_of_creation,
    is_Enable,
    authority_id)
values(
          'Chuan Wei',
          'male',
          'weichuan',
          '19951027',
          '2024-09-25',
          True,
        1
      );

