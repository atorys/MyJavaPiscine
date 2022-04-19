package ex00;

public class Program {

    public static int sumOfDigits(int x) {
        if (x == 0)
            return (0);
        return (x % 10 + sumOfDigits(x / 10));
    }

    public static void main(String []args) {
        System.out.println(sumOfDigits(479598));
        System.out.println(sumOfDigits(1111111111));
        System.out.println(sumOfDigits(999));
    }
}
