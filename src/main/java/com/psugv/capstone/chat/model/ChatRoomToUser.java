package com.psugv.capstone.chat.model;

import com.psugv.capstone.login.model.UserModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * This class is to record the relationship of chat room and user.
 * Author: Chuan Wei
 */
@Setter
@Getter
@Entity(name = "chatRoomToUser")
@Table(name = "chatRoomToUser")
@Component("chatRoomToUser")
public class ChatRoomToUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private UserModel userModel;

    @OneToOne
    @JoinColumn(name="chat_room_id", referencedColumnName = "chat_room_id")
    private ChatRoom chatRoom;

    public ChatRoomToUser() {}

    public ChatRoomToUser(Integer id, UserModel userModel, ChatRoom chatRoom) {
        this.id = id;
        this.userModel = userModel;
        this.chatRoom = chatRoom;
    }
}
