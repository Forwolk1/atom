package ru.atom.http.server;

import java.util.Date;

public class Message {
    private Date stamp;
    private String name;
    private String message;

    public Message(Date stamp, String name, String message) {
        this.stamp = stamp;
        this.name = name;
        this.message = message;
    }

    public Date getStamp() {
        return stamp;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}
