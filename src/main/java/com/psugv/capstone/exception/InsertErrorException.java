package com.psugv.capstone.exception;

public class InsertErrorException extends RuntimeException {

    public InsertErrorException(String message) {

        super(message);
    }

    @Override
    public String getMessage() {

        return "Insert to database failed";
    }
}
