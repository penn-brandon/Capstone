package com.psugv.capstone.util;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChatServer {

    /**
     * The key is char room ID and the value is set of Listeners
     * This map is supposed to track the online user and the chat room they are looking into.
     */
    private static ConcurrentHashMap<Integer, Set<MessageListener>> ONLINE_LISTENER_POOL;

    /**
     * The key is user ID and the value is chat room ID
     * This map is supposed to track the online user and the chat room they are looking into.
     */
    private static ConcurrentHashMap<Integer, Integer> ONLINE_USER_POOL;

    public static void updateOnlineUserPool(Integer userId, Integer chatRoomId, MessageListener listener) {

        Set<MessageListener> temp;

        /*
        Remove old chatroom listener
         */
        if (!ONLINE_USER_POOL.containsKey(userId)) {

            Integer oldChatRoomId = ONLINE_USER_POOL.get(userId);

            temp = ONLINE_LISTENER_POOL.get(oldChatRoomId);

            temp.remove(listener);
        }

        ONLINE_USER_POOL.put(userId, chatRoomId);

        try{
            if(ONLINE_LISTENER_POOL.get(chatRoomId) == null) {

                temp = new HashSet<>();

                temp.add(listener);

            } else {

                temp = ONLINE_LISTENER_POOL.get(chatRoomId);

                temp.add(listener);
            }
            ONLINE_LISTENER_POOL.put(chatRoomId, temp);

        } catch (Exception e){

            e.printStackTrace();
        }
    }

    public static void removeFromOnlineUserPool(Integer userId, MessageListener listener) {

        if (ONLINE_USER_POOL.get(userId) == null) {

            return;
        }

        Integer roomId = ONLINE_USER_POOL.get(userId);

        Set<MessageListener> temp = ONLINE_LISTENER_POOL.get(roomId);

        temp.remove(listener);

        ONLINE_USER_POOL.remove(roomId);
    }

    public static Boolean sentMessage(String message, Integer userId, Integer chatRoomId){

        try {

            if (ONLINE_LISTENER_POOL.containsKey(chatRoomId)) {

                Set<MessageListener> listenerSet = ONLINE_LISTENER_POOL.get(chatRoomId);

                for(MessageListener listener : listenerSet) {

                    if(listener.getUser().getId() == userId){

                        continue;
                    }
                    listener.setMessage(message);
                }
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

        ONLINE_LISTENER_POOL = new ConcurrentHashMap<>();

        ONLINE_USER_POOL = new ConcurrentHashMap<>();
    }
}
