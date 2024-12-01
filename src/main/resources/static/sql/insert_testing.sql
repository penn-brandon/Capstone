use capstone;

insert into 1_ChatRoomName (chat_room_id, admin, chat_room_name, last_modified)
values (1, False, 'Robot', CURRENT_TIMESTAMP);
insert into 2_ChatRoomName (chat_room_id, admin, chat_room_name)
values (1, False, 'Chuan Wei');

insert into 1_message(content, time, senderId, sender)
values ('Hi, how are you?', '2024-09-26 07:23:30', 1, 'Chuan Wei');
insert into 1_message(content, time, senderId, sender)
values ('I am good, how about you?', '2024-09-26 07:23:45', 2, 'Bob the builder');


insert into 3_ChatRoomName (chat_room_id, admin, chat_room_name, last_modified)
values (2, False, 'George the Monster', CURRENT_TIMESTAMP);
insert into 4_ChatRoomName (chat_room_id, admin, chat_room_name, last_modified)
values (2, False, 'Freddy The Creator', CURRENT_TIMESTAMP);
insert into 1_ChatRoomName (chat_room_id, admin, chat_room_name, last_modified)
values (3, False, 'Chilling', CURRENT_TIMESTAMP);
insert into 2_ChatRoomName (chat_room_id, admin, chat_room_name, last_modified)
values (3, False, 'Chilling', CURRENT_TIMESTAMP);
insert into 3_ChatRoomName (chat_room_id, admin, chat_room_name, last_modified)
values (3, False, 'Chilling', CURRENT_TIMESTAMP);


insert into ChatRoomToUser (chat_room_id, user_id)
values (2,3);
insert into ChatRoomToUser (chat_room_id, user_id)
values (2,4);
insert into ChatRoomToUser (chat_room_id, user_id)
values (3,1);
insert into ChatRoomToUser (chat_room_id, user_id)
values (3,2);
insert into ChatRoomToUser (chat_room_id, user_id)
values (3,3);
insert into ChatRoomToUser (chat_room_id, user_id)
values (1,1);
insert into ChatRoomToUser (chat_room_id, user_id)
values (1,2);


insert into 3_message(content, time, senderId, sender)
values ('Hello what is going on', '2024-09-26 07:23:30', 3, 'Freddy The Creator');
insert into 3_message(content, time, senderId, sender)
values ('Hi, how are you?', '2024-09-26 07:23:30', 1, 'Chuan Wei');
insert into 3_message(content, time, senderId, sender)
values ('Hi, how are you?', '2024-09-26 07:23:30', 1, 'Chuan Wei');

insert into 2_message(content, time, senderId, sender)
values ('Hi, how are you?', '2024-09-26 07:23:30', 3, 'Freddy The Creator');
insert into 2_message(content, time, senderId, sender)
values ('Hi, how are you?', '2024-09-26 07:23:30', 3, 'Freddy The Creator');
insert into 2_message(content, time, senderId, sender)
values ('Hi, how are you?', '2024-09-26 07:23:30', 3, 'Freddy The Creator');