package ex04;

import java.util.Scanner;

public class Program {

    public static int       size = 10;
    public static char[]    symbols = new char[size];
    public static int[]     counts = new int[size];

    public static int   placeInSortedCountArray(int newCount) {
        int i = 0;
        while (i < size && counts[i] >= newCount)
            i++;
        return (i == size) ? -1 : i;
    }

    public static void  insert(char symbol, int count, int place) {
        for (int i = size - 2; i >= place; i--) {
            counts[i + 1] = counts[i];
            symbols[i + 1] = symbols[i];
        }
        counts[place] = count;
        symbols[place] = symbol;
    }

//    AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAASSSSSSSSSSSSSSSSSSSSSSSSDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDWEWWKFKKDKKDSKAKLSLDKSKALLLLLLLLLLRTRTETWTWWWWWWWWWWOOOOOOO42

    public static void  printDigit(int digit) {
        if (digit == 0)
            return;
        System.out.print(digit);
        if (digit / 10 == 0)
            System.out.print(" ");
        System.out.print("  ");
    }

    public static int   getPercent(int current) {
        return ((100 * counts[current]) / counts[0]) / 10;
    }

    public static void  printGraph() {
        int index = 0;

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < index; j++)
                System.out.print("#   ");

            if (index < 10 && getPercent(index) == size - i) {
                printDigit(counts[index++]);
                while (index < size && index > 0 && getPercent(index - 1) == getPercent(index))
                    printDigit(counts[index++]);
            }
            System.out.print("\n");

        }
        for (int i = 0; i < size; i++) {
            if (counts[i] == 0)
                break ;
            System.out.printf("%c   ", symbols[i]);
        }
    }

    public static void main(String []args) {
        Scanner     in  = new Scanner(System.in);
        String      inputStr = in.nextLine();
        char[]      charArray = inputStr.toCharArray();
        short[]     countArray = new short[65535];

        if (inputStr.length() == 0)
            System.exit(-1);

        for (int i = 0; i < inputStr.length(); i++)
            countArray[charArray[i]]++;

        int placeIndex;
        for (int j = 0; j < 65535; j++) {
            placeIndex = placeInSortedCountArray(countArray[j]);
            if (placeIndex != -1 && placeIndex < size)
                insert((char) j, countArray[j], placeIndex);
        }

        printGraph();
    }
}
