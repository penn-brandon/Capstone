package com.psugv.capstone.chat.model;

import com.psugv.capstone.login.model.UserModel;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Entity(name = "message")
@Component("message")
public class Message {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="message_id")
    private int id;

    private Date time;

    private String content;

    @ManyToOne
    @JoinColumn(name="id")
    private ChatRoom chatroom;

    private Boolean sender;

    public Message() {}

    public Message(ChatRoom chatroom, String content, int id, Date time) {
        this.chatroom = chatroom;
        this.content = content;
        this.id = id;
        this.time = time;
    }

    public ChatRoom getChatroom() {
        return chatroom;
    }

    public void setChatroom(ChatRoom chatroom) {
        this.chatroom = chatroom;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Boolean getSender() {
        return sender;
    }

    public void setSender(Boolean sender) {
        this.sender = sender;
    }
}
