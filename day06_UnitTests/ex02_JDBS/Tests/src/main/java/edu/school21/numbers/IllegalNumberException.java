package edu.school21.numbers;

public class IllegalNumberException extends RuntimeException {
    @Override
    public String toString() {
        return "Illegal number";
    }
}
