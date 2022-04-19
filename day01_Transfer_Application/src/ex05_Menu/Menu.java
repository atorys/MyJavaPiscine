package ex05_Menu;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class Menu {

    private final TransactionsService   service;
    private final boolean               dev;
    private final Scanner               in = new Scanner(System.in);

    private final String  menuBarStandard = "1. Add a user\n" +
                                            "2. View user balances\n" +
                                            "3. Perform a transfer\n" +
                                            "4. View all transactions for a specific user\n" +
                                            "5. Finish execution";

    private final String  menuBarDev =  "1. Add a user\n" +
                                        "2. View user balances\n" +
                                        "3. Perform a transfer\n" +
                                        "4. View all transactions for a specific user\n" +
                                        "5. DEV – remove a transfer by ID\n" +
                                        "6. DEV – check transfer validity\n" +
                                        "7. Finish execution";

    public Menu(String profile) {
        service = new TransactionsService();
        dev = profile != null && profile.equals("dev");
    }

    public void start() {
        for (;;) {
            System.out.println(dev ? menuBarDev : menuBarStandard);
            if (!in.hasNextInt()) {
                System.out.println("\033[31mCommand number required\033[0m");
                in.nextLine();
                continue;
            }
            switch (in.nextInt()) {
                case 1:
                    addAUser(); break;
                case 2:
                    viewBalance(); break;
                case 3:
                    transfer(); break;
                case 4:
                    viewTransactions(); break;
                case 5: {
                    if (dev) {
                        removeTransfer();
                        break;
                    }
                    else {
                        finish();
                        return;
                    }
                }
                case 6: {
                    if (dev) {
                        checkValidity();
                        break;
                    }
                }
                case 7: {
                    if (dev) {
                        finish();
                        return;
                    }
                }
                default:
                    System.out.println("\033[31mInvalid command\033[0m");
            }
            System.out.println("---------------------------------------------------------");
        }
    }

    public void viewBalance() {
        User    user;
        int     id;

        if (service.emptyService()) {
            emptyError();
            return;
        }
        System.out.println("Enter a user ID");
        id = in.nextInt();
        try {
            user = service.getUser(id);
            System.out.printf("%s - %d\n", user.getName(), service.getBalance(id));
        } catch (UserNotFoundException e) {
            System.out.println(e.toString());
        }
    }
    public void viewTransactions() {
        Transaction[]   tr;
        int     id;

        if (service.emptyService()) {
            emptyError();
            return;
        }
        System.out.println("Enter a user ID");
        id = in.nextInt();
        try {
            tr = service.getTransactions(id);
            for (int i = 0; i < tr.length; ++i) {
                if (id == tr[i].getRecipient().getIdentifier()) {
                    System.out.printf("From %s(id = %d) %d with id = %s\n", tr[i].getSender().getName(),
                            tr[i].getSender().getIdentifier(), tr[i].getTransferAmount(), tr[i].getIdentifier().toString());
                }
                else {
                    System.out.printf("To %s(id = %d) %d with id = %s\n", tr[i].getRecipient().getName(),
                            tr[i].getRecipient().getIdentifier(), tr[i].getTransferAmount(), tr[i].getIdentifier().toString());
                }
            }
        } catch (UserNotFoundException e) {
            System.out.println(e.toString());
        }
    }

    public void transfer() {
        if (service.emptyService()) {
            emptyError();
            return;
        }
        for (;;) {
            System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
            in.nextLine();
            try {
                service.addTransaction(in.nextInt(), in.nextInt(), in.nextInt());
                System.out.println("The transfer is completed");
                break ;

            } catch (UserNotFoundException e) {
                System.out.println(e.toString());
            } catch (IllegalTransactionException e) {
                System.out.println(e.toString());
            } catch (InputMismatchException e) {
                integerError();
            }
        }
    }
    public void removeTransfer() {
        if (service.emptyService()) {
            emptyError();
            return;
        }
        for (;;) {
            System.out.println("Enter a user ID and a transfer ID");
            if (!in.hasNextInt()) {
                integerError();
                continue ;
            }
            Integer usID = in.nextInt();
            String  tID = in.nextLine();
            tID = tID.substring(tID.indexOf(" ") + 1);
            UUID    correctID;
            try {
                correctID = UUID.fromString(tID);
                try {
                    Transaction tr = service.getUser(usID).getTransactions().getByID(correctID);
                    service.removeTransaction(correctID, usID);
                    System.out.printf("Transfer To %s(id = %d) %d removed\n",
                            tr.getRecipient().getName(),
                            service.getUser(usID).getIdentifier(), tr.getTransferAmount());
                    break ;

                } catch (UserNotFoundException e) {
                    System.out.println(e.toString());
                } catch (TransactionNotFoundException e) {
                    System.out.println(e.toString());
                }
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.toString());
            }

        }
    }

    public void checkValidity() {
        System.out.println("Check results:");
        Transaction[] tr = service.checkValidity();
        for (int i = 0; i < tr.length; ++i) {
            System.out.printf("%s(id = %d) has an unacknowledged transfer id = %s from %s(id = %d) for %d\n",
                    tr[i].getRecipient().getName(), tr[i].getRecipient().getIdentifier(), tr[i].getIdentifier().toString(),
                    tr[i].getSender().getName(), tr[i].getSender().getIdentifier(), tr[i].getTransferAmount());
        }
        if (tr.length == 0)
            System.out.println("OK");
    }

    public void addAUser() {
        char[]      money_string;
        int         splitter;
        String      input;
        int         money;

        for (;;) {
            System.out.println("Enter a user name and a balance");
            in.nextLine();
            input = in.nextLine();
            if ((splitter = input.indexOf(" ")) == -1)
                continue ;
            money_string = input.substring(input.indexOf(" ") + 1).toCharArray();
            money = 0;
            for (int i = 0; i < money_string.length; i++)
                money = money*10 + (money_string[i] - '0');
            System.out.printf("User with id = %d is added\n", service.addUser(input.substring(0, splitter), money).getIdentifier());
            break ;
        }
    }

    public void emptyError() {
        System.out.println("Users list is empty! \nTry to add one with command '1'");
    }
    public void integerError() {
        System.out.println("\033[31mNumber required\033[0m");
    }

    public void finish() {
        in.close();
    }
}
