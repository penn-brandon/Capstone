package com.psugv.capstone.exception;

public class NoQueryResultException extends RuntimeException {

    public NoQueryResultException(String message) {

        super(message);
    }

    @Override
    public String getMessage() {

        return "No query result found";
    }
}
