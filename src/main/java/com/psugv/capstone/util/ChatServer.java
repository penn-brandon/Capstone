package com.psugv.capstone.util;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChatServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatServer.class);

    /**
     * The key is char room ID and the value is map of user id to Listeners
     * This map is supposed to track the online user and the chat room they are looking into.
     * So the inner map key is user id and value is listener.
     */
    private static ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, MessageListener>> ONLINE_LISTENER_POOL;

    /**
     * The key is user ID and the value is chat room ID
     * This map is supposed to track the online user and the chat room they are looking into.
     */
    private static ConcurrentHashMap<Integer, Integer> ONLINE_USER_POOL;

    public synchronized static void updateOnlineUserPool(Integer userId, Integer chatRoomId, MessageListener listener) {

        ConcurrentHashMap<Integer, MessageListener> temp;

        MessageListener tempListener = null;

        /*
        Remove old chatroom listener
         */
        if (ONLINE_USER_POOL.containsKey(userId)) {

            LOGGER.debug("User has existing listener!!");

            Integer oldChatRoomId = ONLINE_USER_POOL.get(userId);

            temp = ONLINE_LISTENER_POOL.get(oldChatRoomId);

            tempListener = temp.get(userId);

            temp.remove(userId);
        }

        ONLINE_USER_POOL.put(userId, chatRoomId);

        LOGGER.debug("Establishing new listener!!");
        if (tempListener == null) {

            tempListener = new MessageListener(listener);

        } else {

            tempListener.updateChatRoom(listener);
        }

        Thread thread = new Thread(tempListener::init);

        thread.start();

        try {
            if (!ONLINE_LISTENER_POOL.containsKey(chatRoomId)) {

                temp = new ConcurrentHashMap<>();

            } else {

                temp = ONLINE_LISTENER_POOL.get(chatRoomId);
            }

            temp.put(userId, tempListener);

            ONLINE_LISTENER_POOL.put(chatRoomId, temp);

        } catch (Exception e) {

            LOGGER.error("Error in managing online user pool and listener pool", e);
        }
    }

    public synchronized static void removeFromOnlineUserPool(Integer userId) {

        if (ONLINE_USER_POOL.get(userId) == null) {

            return;
        }
        Integer roomId = ONLINE_USER_POOL.get(userId);

        ConcurrentHashMap<Integer, MessageListener> temp = ONLINE_LISTENER_POOL.get(roomId);

        temp.remove(userId);

        if (temp.isEmpty()) {

            ONLINE_LISTENER_POOL.remove(roomId);
        }
        ONLINE_USER_POOL.remove(userId);
    }

    public static Boolean sentMessage(String message, Integer userId, Integer chatRoomId, String name) {

        LOGGER.debug("message sent to server!!");
        try {

            if (ONLINE_LISTENER_POOL.containsKey(chatRoomId)) {

                LOGGER.debug("Existing users are listening in the chat room {}", chatRoomId);
                ConcurrentHashMap<Integer, MessageListener> listenerMap = ONLINE_LISTENER_POOL.get(chatRoomId);

                LOGGER.debug("Size of Listener pool of this chat room is {}", listenerMap.size());
                for (Map.Entry<Integer, MessageListener> entry : listenerMap.entrySet()) {

                    MessageListener listener = entry.getValue();

                    if (listener.getUser().getId().equals(userId)) {

                        continue;
                    }
                    LOGGER.debug("Sent message to the listener of {}", listener.getUser().getUsername());
                    listener.setMessage(message, name);
                }
            }
        } catch (Exception e) {

            LOGGER.error("Error in sending message to listener", e);
            return false;
        }
        return true;
    }

    @PostConstruct
    public void init() {

        System.out.println("Chat server started");

        ONLINE_LISTENER_POOL = new ConcurrentHashMap<>();

        ONLINE_USER_POOL = new ConcurrentHashMap<>();
    }
}
