package ex01;

import java.util.Scanner;

public class Program {

    public static void isPrime(int digit) {
        boolean answer;
        int     steps = 0, i = 2;

        while (digit % i != 0 && i*i <= digit) {
            steps++;
            i++;
        }

        answer = digit % i != 0;
        steps++;

        System.out.print(answer);
        System.out.print(' ');
        System.out.println(steps);
    }

    public static void main(String []args) {
        Scanner in      = new Scanner(System.in);
        int     digit   = in.nextInt();

        in.close();
        if (digit <= 1) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }
        isPrime(digit);
    }
}
