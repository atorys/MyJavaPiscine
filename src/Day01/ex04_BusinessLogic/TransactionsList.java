package Day01.ex04_BusinessLogic;

import java.util.UUID;

public interface TransactionsList {
    void            addTransaction(Transaction newTransaction);
    void            removeByID(UUID id) throws TransactionNotFoundException;
    Transaction[]   toArray();
}
