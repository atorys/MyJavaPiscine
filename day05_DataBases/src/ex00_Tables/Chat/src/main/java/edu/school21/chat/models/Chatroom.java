package edu.school21.chat.models;

import java.util.LinkedList;
import java.util.UUID;

public class Chatroom {
    private final UUID id;
    private String name;
    private final User owner;
    private LinkedList<Message> messages;

    public Chatroom(String name, User owner) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.owner = owner;
    }
}
