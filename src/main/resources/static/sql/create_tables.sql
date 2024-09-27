use capstone;

drop table IF EXISTS [dbo].[permission];
drop table IF EXISTS [dbo].[user];

create table user(
    user_id int not null identity primary key,
    name nvarchar(max) not null,
    gender nvarchar(10) not null,
    username nvarchar(max) not null,
    password nvarchar(max) not null,
    date_of_creation date not null,
    is_Enable bit not null
);

create table permission(
    id int not null identity primary key,
    user_id int not null foreign key references user(user_id),
    authorityName nvarchar(max) not null
);

insert into user(
    name,
    gender,
    username,
    password,
    date_of_creation,
    is_Enable)
values(
          'Chuan Wei',
          'male',
          'weichuan',
          '19951027',
          '2024-09-25',
          '1',
          1
      );

insert into authorities (
    user_id,
    authorityName)
values(
       1,
       'NORMAL');