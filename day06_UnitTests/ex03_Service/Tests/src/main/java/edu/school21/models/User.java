package edu.school21.models;

import java.util.Objects;

public class User {
    private Long identifier;
    private String login;
    private String password;
    private boolean status;

    public User(Long identifier, String login, String password, boolean status) {
        this.identifier = identifier;
        this.login = login;
        this.password = password;
        this.status = status;
    }

    public Long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return status == user.status && Objects.equals(identifier, user.identifier) && Objects.equals(login, user.login) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, login, password, status);
    }

    @Override
    public String toString() {
        return "User{" +
                "identifier=" + identifier +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                '}';
    }
}
