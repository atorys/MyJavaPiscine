package edu.school21.chat.models;

import java.util.LinkedList;
import java.util.Objects;

public class Chatroom {
    private final Integer id;
    private String name;
    private final User owner;
    private LinkedList<Message> messages;

    public Chatroom(Integer id, String name, User owner, LinkedList<Message> messages) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.messages = messages;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public LinkedList<Message> getMessages() {
        return messages;
    }

    public void setMessages(LinkedList<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chatroom chatroom = (Chatroom) o;
        return Objects.equals(id, chatroom.id)
                && Objects.equals(name, chatroom.name)
                && Objects.equals(owner, chatroom.owner)
                && Objects.equals(messages, chatroom.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, owner, messages);
    }

    @Override
    public String toString() {
        return "Chatroom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", messages=" + messages +
                '}';
    }
}
