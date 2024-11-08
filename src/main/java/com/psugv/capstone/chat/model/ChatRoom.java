package com.psugv.capstone.chat.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
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

}
