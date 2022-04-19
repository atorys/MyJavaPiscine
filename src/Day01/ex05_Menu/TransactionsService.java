package Day01.ex05_Menu;

import java.util.UUID;

public class TransactionsService {
    private UserArrayList           Users;
    private TransactionsLinkedList  Transactions;

    public                  TransactionsService() {
        this.Users = new UserArrayList();
        this.Transactions = new TransactionsLinkedList();
    }

    public Integer          getBalance(Integer id) throws UserNotFoundException {
        return Users.getByID(id).getBalance();
    }

    public User             addUser(User user) {
        Users.addUser(user);
        return user;
    }
    public User             addUser(String name, Integer balance) {
        User newUser = new User(name, balance);
        Users.addUser(newUser);
        return newUser;
    }

    public User             getUser(Integer id) throws UserNotFoundException {
        return Users.getByID(id);
    }
    public boolean          emptyService() {
        return Users.getNumberUsers() == 0;
    }

    public Transaction[]    getTransactions(Integer id) throws UserNotFoundException {
        return Users.getByID(id).getTransactions().toArray();
    }

    public Transaction[]    checkValidity() {
        TransactionsLinkedList  unpaired = new TransactionsLinkedList();
        Transaction[]           array = Transactions.toArray();

        for (int i = 0; i < array.length; i++) {
            if (array[i].getSender().getTransactions().getByID(array[i].getIdentifier()) == null
                || array[i].getRecipient().getTransactions().getByID(array[i].getIdentifier()) == null)
                unpaired.addTransaction(array[i]);
        }

        return unpaired.toArray();
    }

    public void             removeTransaction(UUID id, Integer userID) throws UserNotFoundException, TransactionNotFoundException {
        Users.getByID(userID).getTransactions().removeByID(id);
    }

    public void             addTransaction(Integer senderID, Integer recipientID, Integer amount) throws IllegalTransactionException {
        if (Users.getByID(senderID).getBalance() < amount || amount < 0)
            throw new IllegalTransactionException();
        Transactions.addTransaction(new Transaction(Users.getByID(senderID), Users.getByID(recipientID), amount));
    }
}
