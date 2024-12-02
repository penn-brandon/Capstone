package com.psugv.capstone.chat.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * This is the entity class for chat room.
 *
 * Author: Chuan Wei & Brandon Alker
 */
@Setter
@Getter
@Entity(name = "ChatRoom")
@Table(name = "ChatRoom")
@Component("ChatRoom")
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
