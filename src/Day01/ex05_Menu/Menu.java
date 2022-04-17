package Day01.ex05_Menu;

import java.util.Scanner;

public class Menu {

    private TransactionsService service;
    private Scanner             in;

    private final String  menuBar = "1. Add a user\n" +
                                    "2. View user balances\n" +
                                    "3. Perform a transfer\n" +
                                    "4. View all transactions for a specific user\n" +
                                    "5. DEV – remove a transfer by ID\n" +
                                    "6. DEV – check transfer validity\n" +
                                    "7. Finish execution";

    public Menu() {
        service = new TransactionsService();
        in = new Scanner(System.in);
    }
    public void start() {
        int input;

        for (;;) {
            System.out.println(menuBar);
            input = in.nextInt();
            switch (input) {
                case 1: {
                    addAUser();
                    break;
                }
                case 2: {
                    finish();
                    return;
                }
//                case 3: {
//                    kwdefr;
//                    break;
//                }
//                case 3: {
//                    kwdefr;
//                    break;
//                }
            }
            System.out.println("---------------------------------------------------------");
        }
    }

    public void addAUser() {
        System.out.println("Enter a user name and a balance");
        String[]    input = in.nextLine().split(" ");
        char[]      balance = input[1].toCharArray();
        int         money = 0;

//        for (int i = 0; i < balance.length; i++)
//            money = money*10 + (int)balance[i];
//        System.out.printf("User with id = %d is added\n", service.addUser(input[0], money).getIdentifier());
    }
    public void finish() {
        in.close();
    }
}
