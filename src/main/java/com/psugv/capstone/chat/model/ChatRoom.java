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

    @JsonIgnore
    @OneToMany(mappedBy="chatRoom",fetch= FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<ChatRoomName> chatRoomName;


    private Boolean joinable;

    public ChatRoom(){}

    public ChatRoom(Integer id, Boolean joinable) {
        this.id = id;
        this.joinable = joinable;
    }

    public Set<ChatRoomName> getChatRoomName() {
        return chatRoomName;
    }

    public void setChatRoomName(Set<ChatRoomName> chatRoomName) {
        this.chatRoomName = chatRoomName;
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
