package Day01.ex04_BusinessLogic;

public class UserNotFoundException extends RuntimeException {
    public String what() {
        return "\033[31mUser not found\n\033[0m";
    }

    @Override
    public String toString() { return this.what(); }
}
