package Day01.ex04_BusinessLogic;


import java.util.UUID;

public class TransactionsService {
    private UserArrayList           Users;
    private TransactionsLinkedList  Transactions;

    public                  TransactionsService() {
        this.Users = new UserArrayList();
        this.Transactions = new TransactionsLinkedList();
    }

    public Integer          getBalance(User user)   { return Users.getByID(user.getIdentifier()).getBalance(); }
    public Integer          getBalance(Integer id)  { return Users.getByID(id).getBalance(); }
    public User             getUser(Integer id)     { return Users.getByID(id); }

    public User             addUser(User user) {
        Users.addUser(user);
        return user;
    }
    public User     addUser(String name, Integer balance) {
        User newUser = new User(name, balance);
        Users.addUser(newUser);
        return newUser;
    }

    public Transaction[]    getTransactions(User user)              { return user.getTransactions().toArray(); }
    public Transaction[]    getTransactions(Integer id)             { return Users.getByID(id).getTransactions().toArray(); }
    public void             removeTransaction(UUID id, User user)   { user.getTransactions().removeByID(id); }
    public void             addTransaction(User sender, User recipient, Integer amount) throws IllegalTransactionException {
        if (sender.getBalance() < amount)
            throw new IllegalTransactionException();
        Transactions.addTransaction(new Transaction(sender, recipient, amount));
    }
}
