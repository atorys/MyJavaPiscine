package ex01_ID_Generator;

public class Program {

    public static boolean   uniqueIdCheck(User[] list, int size) {
        for (int i = 0; i < size - 1; i++)
        {
            for (int j = i + 1; j < size; j++)
            {
                if (list[i].getIdentifier().equals(list[j].getIdentifier()))
                    return false;
            }
        }
        return true;
    }

    public static void main(String []args) {

        int     count = 999;
        User[]  list = new User[count];

        for (int i = 0; i < count; i++)
            list[i] = new User("user" + i, 100 * i % 6 + i % 237);

        list[0].displayUser();
        list[569].displayUser();
        list[886].displayUser();
        list[386].displayUser();
        list[453].displayUser();
        list[111].displayUser();

        System.out.println();
        System.out.printf("are identifiers unique? \033[35m%b\033[0m\n", uniqueIdCheck(list, count));
    }
}
