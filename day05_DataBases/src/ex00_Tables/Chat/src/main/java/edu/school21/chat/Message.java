package edu.school21.chat;

import java.util.Date;
import java.util.UUID;

public class Message {
    private final UUID id;
    private final User author;
    private final Chatroom room;
    private final String text;
    private final Date date;

    public Message(User author, Chatroom room, String text) {
        this.id = UUID.randomUUID();
        this.author = author;
        this.room = room;
        this.text = text;
        this.date = new Date();
    }
}
