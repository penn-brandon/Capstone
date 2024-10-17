use capstone;

insert into 1_chatroomname (
    ChatRoom,
    admin,
    chat_room_name)
values (
        1,
        False,
        "Robot");

insert into 2_chatroomname (
    ChatRoom,
    admin,
    chat_room_name)
values (
        1,
        False,
        "Chuan Wei");

insert into 1_message(content, chatroom, sender) values("Hi, how are you?", 1, true);
insert into 1_message(content, chatroom, sender) values("I'm good, how about you?", 1, false);

insert into 2_message(content, chatroom, sender) values("Hi, how are you?", 1, false);
insert into 2_message(content, chatroom, sender) values("I'm good, how about you?", 1, true);