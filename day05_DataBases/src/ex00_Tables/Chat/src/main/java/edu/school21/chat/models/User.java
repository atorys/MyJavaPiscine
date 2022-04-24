package edu.school21.chat.models;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.UUID;

public class User implements Serializable {
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

    @Override
    public String toString() {
        return "User {" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                "}";
    }
}
