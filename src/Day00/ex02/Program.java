package Day00.ex02;

import java.util.Scanner;

public class Program {

    public static int sumOfDigits(int x) {
        if (x == 0)
            return (0);
        return (x % 10 + sumOfDigits(x / 10));
    }

    public static boolean  isPrime(int digit) {
        for (int i = 2; i*i < digit ; i++)
            if (digit % i == 0)
                return false;
        return digit > 1;
    }

    public static void main(String []args) {
        Scanner in = new Scanner(System.in);
        int     coffees = 0, current;

        for (;;) {
            current = in.nextInt();
            if (current == 42)
                break;
            coffees += isPrime(sumOfDigits(current)) ? 1 : 0;
        }

        System.out.print("Count of coffee-request – " + coffees);
        in.close();
    }
}
