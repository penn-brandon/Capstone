package com.psugv.capstone;

import java.util.ArrayList;

public class ChatRoom {
    private final int chat_room_id;
    private ArrayList<User> owners;
    private boolean joinable;
    private ArrayList<User> members;
    private ArrayList<Message> messageList;

    ChatRoom() {
        this.chat_room_id = nextChatRoomID();
        this.owners = new ArrayList<>();
        this.joinable = false;
        this.members = new ArrayList<>();
        this.messageList = new ArrayList<>();
    }

    private int nextChatRoomID() {
        // TODO
        return 0;
    }

    public int getChat_room_id() {
        return chat_room_id;
    }

    public ArrayList<User> getOwners() {
        return owners;
    }

    public boolean isJoinable() {
        return joinable;
    }

    public void setJoinable(boolean joinable) {
        this.joinable = joinable;
    }

    public ArrayList<User> getMembers() {
        return members;
    }

    public void addMember(User user) {
        members.add(user);
    }

    public void removeMember(User user) {
        members.remove(user);
    }

    public ArrayList<Message> getMessageList() {
        return messageList;
    }

    public void addMessage(Message message) {
        messageList.add(message);
    }
}
