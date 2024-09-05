package com.example.capstone;

import java.util.Date;

public class Message {
    private final int message_id;
    private final Date date;
    private final String content;
    private final ChatRoom chatroom;
    private final User sender;

    Message(String content, ChatRoom chatroom, User sender) {
        this.message_id = nextAvailableID();
        this.date = new Date();
        this.content = content;
        this.chatroom = chatroom;
        this.sender = sender;
    }

    //Queries the postgres database for the next available message_id in the current chatroom
    private int nextAvailableID() {
        // TODO
        return 0;
    }

    public int getMessage_id() {
        return message_id;
    }

    public Date getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public ChatRoom getChatroom() {
        return chatroom;
    }

    public User getSender() {
        return sender;
    }
}
