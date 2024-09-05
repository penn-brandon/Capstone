package com.example.capstone;

public class Connections {
    private final User user;
    private final User connection;
    private final boolean block;

    Connections(User user, User connection, boolean block) {
        this.user = user;
        this.connection = connection;
        this.block = block;
    }

    public User getUser() {
        return user;
    }

    public User getConnection() {
        return connection;
    }

    public boolean isBlock() {
        return block;
    }
}
