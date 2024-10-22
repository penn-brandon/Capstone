package com.psugv.capstone.util;

import com.psugv.capstone.chat.model.ChatRoom;
import com.psugv.capstone.chat.model.ChatRoomName;
import com.psugv.capstone.chat.service.IChatService;
import com.psugv.capstone.login.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);

    private boolean listening = false;

    private UserModel user;

    private ChatRoom room;

    private ChatRoomName roomName;

    private String message = MESSAGE_WAIT;

    private final static String MESSAGE_WAIT = "MESSAGE_IS_WAITING_FOR_NEW_INPUT";

    @Autowired
    private IChatService chatService;

    public MessageListener(ChatRoom room, ChatRoomName roomName, UserModel user) {
        this.room = room;
        this.roomName = roomName;
        this.user = user;
    }

    public MessageListener(MessageListener messageListener) {
        this.room = messageListener.getRoom();
        this.roomName = messageListener.getRoomName();
        this.user = messageListener.getUser();
    }

    public MessageListener(){}

    public void init(){

        LOGGER.info("Listening start");
        LOGGER.trace(this.toString());
        listening = true;
        listeringMessage();
    }

    public void destroy (){

        LOGGER.info("Listening stop");
        LOGGER.trace(this.toString() + " !!Ceassed!!");
        listening = false;
        user = null;
        room = null;
        roomName = null;
    }

    public synchronized void updateChatRoom(MessageListener listener) {

        synchronized (this){

            listening = false;
            LOGGER.info("Update chat room");
            LOGGER.trace(this.toString());
            this.room = listener.getRoom();
            this.roomName = listener.getRoomName();
        }
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

                    LOGGER.debug("Message received: " + message);
                    LOGGER.debug("sending message out!!");
                    chatService.sendUpdate(this, message);

                    message = MESSAGE_WAIT;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error occurred by listering loop", e);
            destroy();
        }
        LOGGER.warn(this.toString() + "!!!Stop listening!!!");
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

    @Override
    public String toString(){

        return user.getName() + " is listening to chat room " + roomName;
    }
}
