package com.psugv.capstone.chat.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity(name = "chatroom")
@Table(name = "chatroom")
@Component("chatroom")
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "chat_room_id")
    private Integer id;

    private Boolean joinable;

    public ChatRoom() {
    }

    public ChatRoom(Integer id, Boolean joinable) {
        this.id = id;
        this.joinable = joinable;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getJoinable() {
        return joinable;
    }

    public void setJoinable(Boolean joinable) {
        this.joinable = joinable;
    }
}
