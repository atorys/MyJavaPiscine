package edu.school21.exceptions;

public class AlreadyAuthenticatedException extends RuntimeException {

    @Override
    public String toString() {
        return "User already authenticated";
    }
}
