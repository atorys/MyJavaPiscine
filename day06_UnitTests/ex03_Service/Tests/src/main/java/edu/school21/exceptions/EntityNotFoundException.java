package edu.school21.exceptions;

public class EntityNotFoundException extends RuntimeException {
    @Override
    public String toString() {
        return "User not found";
    }
}
