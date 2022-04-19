package ex00_Models;

import java.util.UUID;

public class  Transaction {
    private final UUID  Identifier;
    private User        Recipient;
    private User        Sender;
    private Category    TransferCategory;
    private Integer     TransferAmount;
    private Conditions  Condition;

    private enum Category {
        debits,
        credits
    }
    private enum Conditions {
        success,
        failure
    }

    public Transaction(User sender, User recipient, Integer amount) {
        this.Identifier = UUID.randomUUID();
        this.Sender = sender;
        this.Recipient = recipient;
        this.TransferAmount = amount > 0 ? amount : -amount;
        this.TransferCategory = amount > 0 ? Category.debits : Category.credits;

        makeTransaction(sender, recipient);
    }

    private void    makeTransaction(User sender, User recipient) {
        this.Condition = Conditions.failure;

        if (sender.getBalance() < this.TransferAmount)
            return ;

        sender.setBalance(sender.getBalance() - this.TransferAmount);
        recipient.setBalance(recipient.getBalance() + this.TransferAmount);
        this.Condition = Conditions.success;
    }

    public UUID     getIdentifier()         { return this.Identifier; }
    public User     getSender()             { return this.Sender; }
    public User     getRecipient()          { return this.Recipient; }
    public Integer  getTransferAmount()     { return this.TransferAmount; }
    public Category getTransferCategory()   { return this.TransferCategory; }

    public void     display() {
        System.out.printf("TRANS_ID\t%s:\t%s\t%d$\n", this.getIdentifier().toString(), this.getTransferCategory().toString(), this.getTransferAmount());
        System.out.printf("\t\t\tfrom:\t%s\n", this.getSender().getName());
        System.out.printf("\t\t\tto:\t\t%s\n", this.getRecipient().getName());
        System.out.printf("\t\t\t%s\033[0m\n", this.Condition == Conditions.success ?
                "\033[32m" + this.Condition : "\033[31m" + this.Condition);
    }
}
