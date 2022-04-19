package ex03_ListOfTransactions;

public class TransactionNotFoundException extends RuntimeException {
    public String what() {
        return "\033[31mTransaction not found\n\033[0m";
    }

    @Override
    public String toString() { return this.what(); }
}
