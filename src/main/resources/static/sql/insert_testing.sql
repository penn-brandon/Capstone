use capstone;

insert into 1_chatroomname (
    ChatRoom,
    admin,
    chat_room_name,
    last_modified)
values (
        1,
        False,
        "Robot",
        CURRENT_TIMESTAMP);

insert into 2_chatroomname (
    ChatRoom,
    admin,
    chat_room_name)
values (
        1,
        False,
        "Chuan Wei");

insert into 1_message(
    content,
    time,
    senderId,
    sender)
values(
    "Hi, how are you?",
       "2024-09-26 07:23:30",
    1,
    "Chuan Wei");

insert into 1_message(
    content,
    time,
    senderId,
    sender)
values(
    "I'm good, how about you?",
    "2024-09-26 07:23:45",
    2,
    "Bob the builder");

