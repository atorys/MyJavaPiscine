package edu.school21.chat;

import java.util.LinkedList;
import java.util.UUID;

public class User {
    private final UUID id;
    private String login;
    private String password;
    private LinkedList<Chatroom> createdRooms;
    private LinkedList<Chatroom> joinedRooms;

    public User(String login, String password) {
        this.id = UUID.randomUUID();
        this.login = login;
        this.password = password;
    }

    public void joinChatroom(Chatroom newRoom) {
        joinedRooms.add(newRoom);
    }
}
