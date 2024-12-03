package com.psugv.capstone.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.psugv.capstone.login.model.UserModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * This class is an entity class that user to present messages in a chat room.
 * Each chat room will have its own message table.
 * Author: Chuan Wei and Brandon Alker
 */
@Setter
@Getter
@Entity(name = "message")
@Component("message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_id")
    @JsonIgnore
    private int id;

    private Date time;

    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "senderId", referencedColumnName = "user_id")
    private UserModel userModel;

    @Transient
    private Integer senderId;

    private String sender;

    public Message() {
    }

    public Message(String content, String sender, UserModel userModel, Date time) {
        this.content = content;
        this.sender = sender;
        this.userModel = userModel;
        senderId = userModel.getId();
        this.time = time == null ? new Date() : time;
    }

}
