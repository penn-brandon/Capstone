package com.psugv.capstone.util;

import com.psugv.capstone.chat.model.ChatRoom;
import com.psugv.capstone.chat.model.ChatRoomName;
import com.psugv.capstone.login.model.UserModel;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the listener class that will pass the message received from the server to the view.
 * Each session from user will have one listener to serve.
 * Author: Chuan Wei
 */
@Component
public class MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);
    private final static String MESSAGE_WAIT = "MESSAGE_IS_WAITING_FOR_NEW_INPUT";
    @Getter
    private boolean listening = false;
    @Getter
    private UserModel user;
    @Getter
    private ChatRoom room;
    @Getter
    private ChatRoomName roomName;
    private String message;
    @Getter
    private SimpMessagingTemplate messagingTemplate;

    private String senderName = null;

    public MessageListener(ChatRoom room, ChatRoomName roomName, UserModel user, SimpMessagingTemplate messagingTemplate) {

        LOGGER.info("Listener initializes");
        this.room = room;
        this.roomName = roomName;
        this.user = user;
        message = MESSAGE_WAIT;
        this.messagingTemplate = messagingTemplate;
        LOGGER.debug("messagingTemplate is null in constructor? {}", messagingTemplate == null);
    }

    public MessageListener(MessageListener messageListener) {

        LOGGER.info("Listener initializes");
        this.room = messageListener.getRoom();
        this.roomName = messageListener.getRoomName();
        this.user = messageListener.getUser();
        message = MESSAGE_WAIT;
        this.messagingTemplate = messageListener.getMessagingTemplate();
        LOGGER.debug("messagingTemplate is null in constructor? {}", messagingTemplate == null);
    }

    public MessageListener() {
    }

    public void init() {

        LOGGER.info("Listening start");
        LOGGER.trace(this.toString());
        listening = true;
        listeningMessage();
    }

    public void destroy() {

        LOGGER.info("Listening stop");
        LOGGER.trace("{} !!Ceased!!", this);
        listening = false;
        user = null;
        room = null;
        roomName = null;
    }

    public synchronized void updateChatRoom(MessageListener listener) {

        LOGGER.debug("Lock listener itself");
        synchronized (this) {

            listening = false;

            LOGGER.info("Update chat room");
            LOGGER.trace(this.toString());
            this.room = listener.getRoom();
            this.roomName = listener.getRoomName();
            LOGGER.debug("messagingTemplate is null in update method? {}", messagingTemplate == null);
        }
    }

    public synchronized void setMessage(String message, String name) {

        LOGGER.debug("In setMessage method");

        synchronized (this) {

            LOGGER.debug("Message: {} synchronized, set message field and notify it.", this.message);
            this.message = message;
            this.senderName = name;
            this.notify();
        }
    }

    private void listeningMessage() {

        try {
            synchronized (this) {

                while (listening) {

                    if (message.equals(MESSAGE_WAIT)) {

                        LOGGER.debug("Message object wait!!");
                        this.wait();
                    }
                    LOGGER.debug("Message received: {} sender is {}", message, this.senderName);
                    LOGGER.trace("sending message out!!");
                    sendUpdateToSocket(message, senderName);

                    message = MESSAGE_WAIT;
                    senderName = null;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error occurred by listening loop", e);
            destroy();
        }
        LOGGER.warn("!!!Stop listening!!!");
    }

    @Override
    public String toString() {

        return user.getName() + " is listening to chat room " + roomName;
    }

    private void sendUpdateToSocket(String message, String senderName) {

        LOGGER.debug("ChatService.sendUpdate, message is: {}, and Listener is: {}", message, this.getUser().getUsername());
        String userName = this.getUser().getUsername();

        LOGGER.info("Publish the data to /listening/{}", userName);
        LOGGER.debug("messagingTemplate is null? {}", messagingTemplate == null);

        Map<String, Object> result = new HashMap<>();

        result.put("message", message);
        result.put("senderName", senderName);

        messagingTemplate.convertAndSend("/listening/" + userName, result);
    }

    public void setMessagingTemplate(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
}
