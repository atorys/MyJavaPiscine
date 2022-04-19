package ex02_ListOfUsers;

public class UserNotFoundException extends RuntimeException {
    public String what() {
        return "\033[31mUser not found\n\033[0m";
    }

    @Override
    public String toString() { return this.what(); }
}
