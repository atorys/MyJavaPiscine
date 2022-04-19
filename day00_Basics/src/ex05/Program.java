package ex05;

import java.util.Scanner;

public class Program {

    private static final int    HERE = 1;
    private static final int    NOT_HERE = 0;

    public static String        startDate = "01.09.2020";
    public static int           size = 10;
    public static String[]      studentsNames = new String[size];
    public static String[]      classDays = new String[size];
    public static String[]      classStart = new String[size];

    public static String[]      attendance = new String[1000];

    public static int   findIndex(String[] where, String key) {
        for (int i = 0; i < 10; i++)
            if (where[i].equals(key))
                return i;
        return -1;
    }

    public static void  input() {
        Scanner     in  = new Scanner(System.in);
        String      key = "";

        for (int i = 0; i < size; i++) {
            studentsNames[i] = in.nextLine();
            if (studentsNames[i].equals(".")) {
                studentsNames[i] = null;
                break;
            }
        }
        for (int i = 0; i < size; i++) {
            classStart[i] = in.nextLine();
            if (classStart[i].equals(".")) {
                classStart[i] = null;
                break;
            }
            classDays[i] = in.nextLine();
        }

        for (int i = 0; i < 1000; ++i) {
            key = in.nextLine();
            if (key.equals("."))
                break;
            attendance[i] += findIndex(studentsNames, key) + ",";
            key = in.nextLine();
            attendance[i] += findIndex(classStart, key) + ",";
            key = in.nextLine();
            attendance[i] += key + ",";
            key = in.nextLine();
            if (key.equals("NOT_HERE"))
                attendance[i] += NOT_HERE;
            else
                attendance[i] += HERE;
        }
    }

    public static void  output() {

    }


    public static void  main(String []args) {
        input();

    }
}
