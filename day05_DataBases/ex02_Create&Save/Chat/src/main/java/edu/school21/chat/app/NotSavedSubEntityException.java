package edu.school21.chat.app;

public class NotSavedSubEntityException extends RuntimeException {
    @Override
    public String toString() {
        return "Couldn't save the message";
    }
}
