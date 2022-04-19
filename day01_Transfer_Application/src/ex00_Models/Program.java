package ex00_Models;

public class Program {
    public static void main(String []args) {

        User u1 = new User(0, "Masha", 100);
        User u2 = new User(1, "Lesha", 30);
        User u3 = new User(2, "Pasha", 555);

        u1.displayUser();
        u2.displayUser();
        u3.displayUser();
        System.out.println();

        u1.setBalance(856);
        u1.displayUser();

        u2.setName("richer " + u2.getName());
        u2.setBalance(u2.getBalance() * 2);
        u2.displayUser();
        System.out.println();

        Transaction salary = new Transaction(u1, u2, -30);
        salary.display();
        u1.displayUser();
        u2.displayUser();
        System.out.println();

        Transaction salary2 = new Transaction(u1, u2, 900);
        salary2.display();
        u1.displayUser();
        u2.displayUser();
    }
}
