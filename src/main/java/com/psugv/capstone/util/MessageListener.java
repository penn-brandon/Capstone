package com.psugv.capstone.util;

import com.psugv.capstone.chat.model.ChatRoom;
import com.psugv.capstone.chat.model.ChatRoomName;
import com.psugv.capstone.login.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private boolean listening = false;

    private UserModel user;

    private ChatRoom room;

    private ChatRoomName roomName;

    private String message = MESSAGE_WAIT;

    private final static String MESSAGE_WAIT = "MESSAGE_IS_WAITING_FOR_NEW_INPUT";

    public MessageListener(ChatRoom room, ChatRoomName roomName, UserModel user) {
        this.room = room;
        this.roomName = roomName;
        this.user = user;
    }

    public void init(){

        System.out.println("Listening start");
        listening = true;
        listeringMessage();
    }

    public void destroy (){

        System.out.println("Listening stop");
        listening = false;
        user = null;
        room = null;
        roomName = null;
    }

    public void updateChatRoom(ChatRoom room, ChatRoomName roomName) {

        listening = false;
        System.out.println("Update chat room");
        this.room = room;
        this.roomName = roomName;
        init();
    }

    public synchronized void setMessage(String message) {
        this.message = message;
        this.message.notify();
    }

    private void listeringMessage(){

        try {
            synchronized (message){

                while(listening){

                    if(message.equals(MESSAGE_WAIT)){

                        message.wait();
                    }


                    message = MESSAGE_WAIT;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            destroy();
        }
        System.out.println("Stop listening ");
    }

    @Override
    public int hashCode(){

        return user.getId();
    }

    public boolean isListening() {
        return listening;
    }

    public ChatRoom getRoom() {
        return room;
    }

    public ChatRoomName getRoomName() {
        return roomName;
    }

    public UserModel getUser() {
        return user;
    }
}
