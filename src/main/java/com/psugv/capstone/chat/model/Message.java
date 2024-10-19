package com.psugv.capstone.chat.model;

import com.psugv.capstone.login.model.UserModel;
import jakarta.persistence.*;
import org.apache.ibatis.annotations.One;
import org.springframework.stereotype.Component;

import java.util.Date;

@Entity(name = "message")
@Component("message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_id")
    private int id;

    private Date time;

    private String content;

    @OneToOne(cascade = CascadeType.ALL)
    private UserModel senderId;

    private String sender;

    public Message() {
    }

    public Message(String content, String sender, UserModel senderId, Date time) {
        this.content = content;
        this.sender = sender;
        this.senderId = senderId;
        this.time = time == null? new Date() : time;
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

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public UserModel getSenderId() {
        return senderId;
    }

    public void setSenderId(UserModel senderId) {
        this.senderId = senderId;
    }
}
