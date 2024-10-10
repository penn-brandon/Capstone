package com.psugv.capstone.util;

import com.psugv.capstone.chat.model.ChatRoom;
import com.psugv.capstone.login.model.UserModel;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChatServer {

    /**
     * The key is user ID and the value is Listener
     * This map is supposed to track the online user and the chat room they are looking into.
     */
    private static ConcurrentHashMap<Integer, MessageListener> onlineUserPool;

    public static ConcurrentHashMap<Integer, MessageListener> getOnlineUserPool() {

        return onlineUserPool;
    }
    public static void updateOnlineUserPool(Integer userId, MessageListener listener) {

        onlineUserPool.put(userId, listener);
    }

    public static void removeFromOnlineUserPool(Integer userId) {

        onlineUserPool.remove(userId);
    }

    public static Boolean sentMessage(String message, Integer userId){

        try {
            if (onlineUserPool.containsKey(userId)) {

                MessageListener listener = onlineUserPool.get(userId);
                listener.setMessage(message);
            }
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
        return true;
    }

    @PostConstruct
    public void init() {

        System.out.println("Chat server started");

        onlineUserPool = new ConcurrentHashMap<>();
    }
}
