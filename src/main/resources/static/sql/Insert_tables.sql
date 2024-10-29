use capstone;

insert into authorities (user_id,
                         authorityName)
values (1,
        'NORMAL');

insert into authorities (user_id,
                         authorityName)
values (2,
        'NORMAL');

insert into user(name,
                 gender,
                 username,
                 password,
                 date_of_creation,
                 is_Enable)
values ('Chuan Wei',
        'male',
        'weichuan',
        '1234',
        '2024-09-25',
        TRUE);

insert into user(name,
                 gender,
                 username,
                 password,
                 date_of_creation,
                 is_Enable)
values ('Bob the builder',
        'male',
        'robot',
        '1234',
        '2024-09-25',
        TRUE);

insert into chatroom(joinable)
values (FALSE);
insert into chatroom(joinable)
values (FALSE);