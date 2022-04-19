package ex03;

import java.util.Scanner;

public class Program {

    static long pack;

    static void pack(int minGrade) {
        long copy = pack;

        while (copy > 0) {
            minGrade *= 10;
            copy /= 10;
        }
        minGrade *= 10;
        pack += minGrade;
    }
    static void unpack(int weekNum, int max) {
        if (weekNum == max)
            return;
        System.out.print("Week " + weekNum + " ");

        for (long i = (pack % 100) / 10; i > 0; i--)
            System.out.print("=");
        System.out.println(">");

        pack /= 100;
        unpack(weekNum + 1, max);
    }

    public static void main(String []args) {
        Scanner in = new Scanner(System.in);
        String  week;
        int     min, curr, weekNum;

        for (weekNum = 1; weekNum < 19; weekNum++) {
            week = in.nextLine();

            if (week.equals("42"))
                break;
            else if (!week.equals("Week " + weekNum)) {
                in.close();
                System.err.println("Illegal Argument");
                System.exit(-1);
            }

            min = 10;
            for (int j = 0; j < 5; j++)
                min = (curr = in.nextInt()) < min ? curr : min;
            in.nextLine();
            pack(min);
        }
        unpack(1, weekNum);
        in.close();
    }
}
