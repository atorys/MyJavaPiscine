package edu.school21.chat.models;

import java.util.LinkedList;
import java.util.Objects;

public class User {
    private final Integer id;
    private String login;
    private String password;
    private LinkedList<Chatroom> createdRooms;
    private LinkedList<Chatroom> joinedRooms;

    public User(Integer id, String login, String password, LinkedList<Chatroom> createdRooms, LinkedList<Chatroom> joinedRooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdRooms = createdRooms;
        this.joinedRooms = joinedRooms;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public LinkedList<Chatroom> getCreatedRooms() {
        return createdRooms;
    }

    public LinkedList<Chatroom> getJoinedRooms() {
        return joinedRooms;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreatedRooms(LinkedList<Chatroom> createdRooms) {
        this.createdRooms = createdRooms;
    }

    public void setJoinedRooms(LinkedList<Chatroom> joinedRooms) {
        this.joinedRooms = joinedRooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id)
                && Objects.equals(login, user.login)
                && Objects.equals(password, user.password)
                && Objects.equals(createdRooms, user.createdRooms)
                && Objects.equals(joinedRooms, user.joinedRooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, createdRooms, joinedRooms);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createdRooms=" + createdRooms +
                ", joinedRooms=" + joinedRooms +
                '}';
    }
}
