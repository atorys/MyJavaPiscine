package Day01.ex05_Menu;

import java.util.UUID;

public interface TransactionsList {
    void            addTransaction(Transaction newTransaction);
    void            removeByID(UUID id) throws TransactionNotFoundException;
    Transaction[]   toArray();
}
