use capstone;

insert into user(name,
                 gender,
                 username,
                 password,
                 is_Enable)
values ('Chuan Wei',
        'male',
        'weichuan',
        '1234',
        TRUE);

insert into user(name,
                 gender,
                 username,
                 password,
                 is_Enable)
values ('Bob the builder',
        'male',
        'robot',
        '1234',
        TRUE);

insert into user(name,
                 gender,
                 username,
                 password,
                 is_Enable)
values ('Freddy The Creator',
        'male',
        'freddy',
        '1234',
        TRUE);

insert into user(name,
                 gender,
                 username,
                 password,
                 is_Enable)
values ('George the Monster',
        'female',
        'purdue',
        '1234',
        TRUE);

insert into authorities (user_id,
                         authorityName)
values (1,
        'NORMAL');

insert into authorities (user_id,
                         authorityName)
values (2,
        'NORMAL');

insert into authorities (user_id,
                         authorityName)
values (3,
        'NORMAL');
insert into authorities (user_id,
                         authorityName)
values (4,
        'NORMAL');

insert into ChatRoom(joinable)
values (FALSE);
insert into ChatRoom(joinable)
values (FALSE);
insert into ChatRoom(joinable)
values (FALSE);