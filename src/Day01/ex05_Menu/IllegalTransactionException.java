package Day01.ex05_Menu;

public class IllegalTransactionException extends RuntimeException {
    public String what() {
        return "\033[31mNot enough moneyy ;(( \n\033[0m";
    }

    @Override
    public String toString() { return this.what(); }
}