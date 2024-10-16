package com.psugv.capstone.exception;

public class NoChatRoomException extends RuntimeException{

    @Override
    public String getMessage() {

        return "No chat room found";
    }

    public NoChatRoomException(String message) {

        super(message);
    }
}
