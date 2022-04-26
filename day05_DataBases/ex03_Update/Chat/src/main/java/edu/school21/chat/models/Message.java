package edu.school21.chat.models;

import java.util.Date;
import java.util.Objects;

public class Message {
    private Integer id;
    private User author;
    private Chatroom room;
    private String text;
    private Date date;

    public Message(Integer id, User author, Chatroom room, String text, Date date) {
        this.id = id;
        this.author = author;
        this.room = room;
        this.text = text;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public Chatroom getRoom() {
        return room;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setRoom(Chatroom room) {
        this.room = room;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id)
                && Objects.equals(author, message.author)
                && Objects.equals(room, message.room)
                && Objects.equals(text, message.text)
                && Objects.equals(date, message.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, room, text, date);
    }

    @Override
    public String toString() {
        return "Message : {" +
                "id=" + id +
                ",\n author=" + author +
                ",\n room=" + room +
                ",\n text='" + text + '\'' +
                ",\n date=" + date +
                "\n}";
    }
}
