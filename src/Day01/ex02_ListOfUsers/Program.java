package Day01.ex02_ListOfUsers;

public class Program {


    public static void main(String []args) {
        test1();
        test2();
        test3();
    }


    public static void test1() {
        System.out.println("\033[35mTEST 1\033[0m");

        User u1 = new User("Masha", 100);
        User u2 = new User("Lesha", 0);
        User u3 = new User("Pasha", 555);

        UserArrayList list = new UserArrayList();
        list.addUser(u2);
        list.addUser(u1);
        list.addUser(u3);
        list.showList();

        list.getByID(0).displayUser();
        list.getByIndex(0).displayUser();

        System.out.println("Count users = " + list.getNumberUsers());
    }

    public static void test2() {
        System.out.println("\033[35mTEST 2\033[0m");

        UserArrayList   list = new UserArrayList();
        int             size = 1000;

        for (int i = 0; i < size; i++)
            list.addUser(new User("user" + i, 100 * i % 6 + i % 237));

        System.out.println("Count users = " + list.getNumberUsers());
        list.showListHead(3);
        System.out.println("(...)");
        list.showListTail(3);
    }

    public static void test3() {
        System.out.println("\033[35mTEST 3\033[0m");

        UserArrayList   list = new UserArrayList();
        int             size = 50;

        for (int i = 0; i < size; i++)
            list.addUser(new User("user" + i, 100 * i % 6 + i % 237));

        System.out.println("Count users = " + list.getNumberUsers());
        list.showListHead(3);
        System.out.println("(...)");
        list.showListTail(3);
        System.out.println();

        try {
            System.out.println("User with id=1050 :" + list.getByID(1050).getName());
            System.out.println("User with id=1051 :" + list.getByID(1051).getName());
            System.out.println("User with id=333  :" + list.getByID(333).getName());
        }
        catch (UserNotFoundException e) {
            System.out.println(e.toString());
        }

        try {
            System.out.println("User with ix=1  :" + list.getByIndex(1).getName());
            System.out.println("User with ix=49 :" + list.getByIndex(49).getName());
            System.out.println("User with ix=50 :" + list.getByIndex(50).getName());
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.toString());
        }
    }
}
