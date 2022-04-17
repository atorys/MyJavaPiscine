package Day01.ex03_ListOfTransactions;

import java.util.UUID;

public class Program {
    public static void main(String []args) {

        User u1 = new User("Masha", 100);
        User u2 = new User("Lesha", 30);
        User u3 = new User("Pasha", 555);

        Transaction t1 = new Transaction(u3, u1, 1);
        Transaction t2 = new Transaction(u3, u2, 2);
        Transaction t3 = new Transaction(u3, u1, 3);

        System.out.printf("%s`s transaction list :\n", u3.getName());
        u3.getTransactions().display();
        System.out.println();

        System.out.printf("%s`s transaction list :\n", u1.getName());
        u1.getTransactions().display();
        System.out.println();

        try {
            u3.getTransactions().removeByID(t3.getIdentifier());
            u3.getTransactions().removeByID(UUID.randomUUID());
        }
        catch (TransactionNotFoundException e) {
            System.out.println(e.toString());
        }

        System.out.printf("%s`s transaction list :\n", u3.getName());
        u3.getTransactions().display();
        System.out.println();

        Transaction[] tr = u3.getTransactions().toArray();
        for (int i = 0; i < tr.length; i++)
            tr[i].display();

        System.out.println(u3.getBalance());
    }
}
