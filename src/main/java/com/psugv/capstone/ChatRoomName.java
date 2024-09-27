package com.psugv.capstone;

public class ChatRoomName {
    private final User user;
    private final ChatRoom chatRoom;
    private String chat_room_name;

    ChatRoomName(User user, ChatRoom chatRoom, String chat_room_name) {
        this.user = user;
        this.chatRoom = chatRoom;
        this.chat_room_name = chat_room_name;
    }

    public User getUser() {
        return user;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public String getChat_room_name() {
        return chat_room_name;
    }

    public void setChat_room_name(String chat_room_name) {
        this.chat_room_name = chat_room_name;
    }
}
