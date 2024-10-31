use capstone;

insert into 1_chatroomname (chat_room_id, admin, chat_room_name, last_modified)
values (1, False, "Robot", CURRENT_TIMESTAMP);

insert into 2_chatroomname (chat_room_id, admin, chat_room_name)
values (1, False, "Chuan Wei");

insert into 1_message(content, time, senderId, sender)
values ("Hi, how are you?", "2024-09-26 07:23:30", 1, "Chuan Wei");

insert into 1_message(content, time, senderId, sender)
values ("I'm good, how about you?", "2024-09-26 07:23:45", 2, "Bob the builder");


insert into 3_chatroomname (chat_room_id, admin, chat_room_name, last_modified)
values (2, False, "George the Monster", CURRENT_TIMESTAMP);
insert into 4_chatroomname (chat_room_id, admin, chat_room_name, last_modified)
values (2, False, "Freddy The Creator", CURRENT_TIMESTAMP);

#Chat Room
insert into 1_chatroomname (chat_room_id, admin, chat_room_name, last_modified)
values (3, False, "Chilling", CURRENT_TIMESTAMP);
insert into 2_chatroomname (chat_room_id, admin, chat_room_name, last_modified)
values (3, False, "Chilling", CURRENT_TIMESTAMP);
insert into 3_chatroomname (chat_room_id, admin, chat_room_name, last_modified)
values (3, False, "Chilling", CURRENT_TIMESTAMP);

insert into 3_message(content, time, senderId, sender)
values ("Hello what's going on", "2024-09-26 07:23:30", 3, "Freddy The Creator");
insert into 3_message(content, time, senderId, sender)
values ("Hi, how are you?", "2024-09-26 07:23:30", 1, "Chuan Wei");
insert into 3_message(content, time, senderId, sender)
values ("Hi, how are you?", "2024-09-26 07:23:30", 1, "Chuan Wei");

insert into 2_message(content, time, senderId, sender)
values ("Hi, how are you?", "2024-09-26 07:23:30", 3, "Freddy The Creator");
insert into 2_message(content, time, senderId, sender)
values ("Hi, how are you?", "2024-09-26 07:23:30", 3, "Freddy The Creator");
insert into 2_message(content, time, senderId, sender)
values ("Hi, how are you?", "2024-09-26 07:23:30", 3, "Freddy The Creator");