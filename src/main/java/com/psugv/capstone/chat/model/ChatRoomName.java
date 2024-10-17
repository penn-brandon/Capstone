package com.psugv.capstone.chat.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Entity(name = "chatRoomName")
@Component("chatRoomName")
public class ChatRoomName {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="chat_room_name_id")
    private Integer id;

    @Column(name="chat_room_name")
    private String chatRoomName;

    @OneToOne
    @JoinColumn(name="id")
    private ChatRoom chatRoom;

    private Boolean admin;

    @Column(name="last_modified")
    private Date lastModified;

    public ChatRoomName(){}

    public ChatRoomName(Boolean admin, ChatRoom chatRoom, String chatRoomName, Integer id, Date lastModified) {
        this.admin = admin;
        this.chatRoom = chatRoom;
        this.chatRoomName = chatRoomName;
        this.id = id;
        this.lastModified = lastModified;
    }

    public String getChatRoomName() {
        return chatRoomName;
    }

    public void setChatRoomName(String chatRoomName) {
        this.chatRoomName = chatRoomName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
}
