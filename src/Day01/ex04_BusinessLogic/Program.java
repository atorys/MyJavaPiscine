package Day01.ex04_BusinessLogic;

public class Program {
    public static void main(String []args) {
        TransactionsService service = new TransactionsService();

        service.addUser("Roma", 770);
        service.addUser("Dima", 0);

        User checker = new User("Checker", 999);
        service.addUser(checker);

        System.out.println(service.getBalance(0));
        System.out.println(service.getBalance(1));
        System.out.println(service.getBalance(2));
        System.out.println();

        try {
            service.addTransaction(0, 1, 25);
            service.addTransaction(1, 0, 5);
            service.addTransaction(2, 0, 999);
            service.addTransaction(1, 0, 25);
        }
        catch (IllegalTransactionException e){
            System.out.println(e.toString());
        }
        System.out.println(service.getBalance(0));
        System.out.println(service.getBalance(1));
        System.out.println(service.getBalance(2));
        System.out.println();

        Transaction[]   tr = service.getTransactions(0);
        System.out.println("user 0 transactions :");
        for (int i = 0; i < tr.length; i++)
            tr[i].display();
        System.out.println();

        tr = service.getTransactions(2);
        System.out.println("user 2 transactions :");
        for (int i = 0; i < tr.length; i++) {
            tr[i].display();
            checker.getTransactions().removeByID(tr[i].getIdentifier());
        }
        System.out.println();

        tr = service.checkValidity();
        System.out.println("unpaired transactions :");
        for (int i = 0; i < tr.length; i++)
            tr[i].display();

    }
}
