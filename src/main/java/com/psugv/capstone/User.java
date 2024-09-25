package com.psugv.capstone;

import java.util.ArrayList;
import java.util.Date;

public class User {
    private final int user_id;
    private final Date date_of_creation;

    private String user_name;
    private String password;
    private Permission permission;
    private ArrayList<ChatRoom> chatRoomList;

    User(String user_name, String password, Permission permission) {
        this.user_id = nextUserID();
        this.date_of_creation = new Date();
        this.user_name = user_name;
        this.password = password;
        this.permission = permission;
        this.chatRoomList = new ArrayList<>();
    }

    private int nextUserID() {
        // TODO
        return 0;
    }

    public int getUser_id() {
        return user_id;
    }

    public Date getDate_of_creation() {
        return date_of_creation;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPassword() {
        return password;
    }

    public Permission getPermission() {
        return permission;
    }

    public ArrayList<ChatRoom> getChatRoomList() {
        return chatRoomList;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public void addChatRoom(ChatRoom chatRoom) {
        this.chatRoomList.add(chatRoom);
    }

    public void removeChatRoom(ChatRoom chatRoom) {
        this.chatRoomList.remove(chatRoom);
    }
}
