package com.psugv.capstone.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.psugv.capstone.login.model.UserAuthorityModel;
import com.psugv.capstone.login.model.UserModel;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Entity(name = "chatroom")
@Table(name = "chatroom")
@Component("chatroom")
public class ChatRoom {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="chat_room_id")
    private Integer id;

    private Boolean joinable;

    public ChatRoom(){}

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
