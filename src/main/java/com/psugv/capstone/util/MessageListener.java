package com.psugv.capstone.util;

import com.psugv.capstone.chat.model.ChatRoom;
import com.psugv.capstone.chat.model.ChatRoomName;
import com.psugv.capstone.login.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);

    private boolean listening = false;

    private UserModel user;

    private ChatRoom room;

    private ChatRoomName roomName;

    private String message;

    private final static String MESSAGE_WAIT = "MESSAGE_IS_WAITING_FOR_NEW_INPUT";

    private SimpMessagingTemplate messagingTemplate;

    public MessageListener(ChatRoom room, ChatRoomName roomName, UserModel user, SimpMessagingTemplate messagingTemplate) {

        LOGGER.info("Listener initializes");
        this.room = room;
        this.roomName = roomName;
        this.user = user;
        message = MESSAGE_WAIT;
        this.messagingTemplate = messagingTemplate;
        LOGGER.debug("messagingTemplate is null in constructor? " + (messagingTemplate == null));
    }

    public MessageListener(MessageListener messageListener) {

        LOGGER.info("Listener initializes");
        this.room = messageListener.getRoom();
        this.roomName = messageListener.getRoomName();
        this.user = messageListener.getUser();
        message = MESSAGE_WAIT;
        this.messagingTemplate = messageListener.getMessagingTemplate();
        LOGGER.debug("messagingTemplate is null in constructor? " + (messagingTemplate == null));
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

        LOGGER.debug("Lock listener itself");
        synchronized (this){

            listening = false;

            LOGGER.info("Update chat room");
            LOGGER.trace(this.toString());
            this.room = listener.getRoom();
            this.roomName = listener.getRoomName();
            LOGGER.debug("messagingTemplate is null in update method? " + (messagingTemplate == null));
        }
    }

    public synchronized void setMessage(String message) {

        LOGGER.debug("In setMessage method");

        synchronized (this) {

            LOGGER.debug("Message: " + this.message + " synchronized, set message field and notify it.");
            this.message = message;

            this.notify();
        }
    }

    private void listeringMessage(){

        try {
            synchronized (this){

                while(listening){

                    if(message.equals(MESSAGE_WAIT)){

                        LOGGER.debug("Message object wait!!");
                        this.wait();
                    }
                    LOGGER.debug("Message received: " + message);
                    LOGGER.trace("sending message out!!");
                    sendUpdateToSocket(message);

                    message = MESSAGE_WAIT;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error occurred by listering loop", e);
            destroy();
        }
        LOGGER.warn("!!!Stop listening!!!");
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

    private void sendUpdateToSocket(String message){

        LOGGER.debug("ChatService.sendUpdate, message is: " + message + ", and Listener is: " + (this.getUser().getUsername()));
        String userName = this.getUser().getUsername();

        LOGGER.info("Publish the data to " + "/listening/" + userName);
        LOGGER.debug("messagingTemplate is null? " + (messagingTemplate == null));
        messagingTemplate.convertAndSend("/listening/" + userName, message);
    }

    public SimpMessagingTemplate getMessagingTemplate() {
        return messagingTemplate;
    }

    public void setMessagingTemplate(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
}
