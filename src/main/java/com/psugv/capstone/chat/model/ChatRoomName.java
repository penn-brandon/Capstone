package com.psugv.capstone.chat.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;


@Entity
@Component("chatRoomName")
public class ChatRoomName {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_name_id")
    private Integer id;

    @Setter
    @Getter
    @Column(name = "chat_room_name", columnDefinition = "TEXT")
    private String chatRoomName;

    @Setter
    @Getter
    @OneToOne
    @JoinColumn(name = "chat_room_id", referencedColumnName = "chat_room_id")
    private ChatRoom chatRoom;

    @Setter
    @Getter
    private Boolean admin;

    @Setter
    @Getter
    @Column(name = "last_modified")
    private Date lastModified;

    public ChatRoomName() {
    }

    public ChatRoomName(Integer id, Boolean admin, ChatRoom chatRoom, String chatRoomName, Date lastModified) {
        this.id = id;
        this.admin = admin;
        this.chatRoom = chatRoom;
        this.chatRoomName = chatRoomName;
        this.lastModified = lastModified;
    }

    @Override
    public boolean equals(Object o) {

        ChatRoomName other = (ChatRoomName) o;

        return this.chatRoomName.equals(other.chatRoomName) && this.id == other.id;
    }
}
