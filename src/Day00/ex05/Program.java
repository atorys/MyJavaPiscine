package Day00.ex05;

import java.util.Scanner;

public class Program {

    public static String        startDate = "01.09.2020";
    public static int           size = 10;
    public static String[]      studentsNames = new String[size];
    public static String[]      classNames = new String[size];
    public static String[]      classStart = new String[size];

//    public static String[][]    schedule = new String[][];


    public static void main(String []args) {
        Scanner             in  = new Scanner(System.in);
//        System.out.println(new SimpleDateFormat("EEEE").format(startDay));


//            for (int i = 0; i < 10; i++) {
//                studentsNames[i] = in.nextLine();
//                if (studentsNames[i].equals(".")) {
//                    studentsNames[i] = null;
//                    break;
//                }
//            }
            for (int i = 0; i < 10; i++) {
                classStart[i] = in.nextLine();
                if (classStart[i].equals(".")) {
                    classStart[i] = null;
                    break;
                }
                classNames[i] = in.nextLine();
            }
            for (int i = 0; i < 10; i++) {
                classStart[i] = in.nextLine();
                if (classStart[i].equals(".")) {
                    classStart[i] = null;
                    break;
                }
                classNames[i] = in.nextLine();
            }
    }
}
