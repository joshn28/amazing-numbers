package numbers;

import java.util.Scanner;
public class Main {

    public static boolean isPalindromic(long num) {
        String strNum = Long.toString(num);
        StringBuilder reversedNum = new StringBuilder();

        for (int i = strNum.length() - 1; i >= 0; i--) {
            reversedNum.append(strNum.charAt(i));
        }

        return strNum.contentEquals(reversedNum);
    }

    public static void showNumProperties(long num) {
        boolean even = num % 2 == 0;
        boolean odd = num % 2 != 0;
        boolean buzz = num % 7 == 0 || num % 10 == 7;
        boolean duck = Long.toString(num).contains("0");
        boolean palindromic = isPalindromic(num);

        System.out.printf("""
                %nProperties of %d
                        even: %b
                        odd: %b
                        buzz: %b
                        duck: %b
                palindromic: %b
                %n""", num, even, odd, buzz, duck, palindromic);
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!");
        System.out.printf("""
                %nSupported requests:
                - enter a natural number to know its properties;
                - enter 0 to exit.
                """);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a request: ");

            long num = scanner.nextLong();

            if (num > 0) {
                showNumProperties(num);
            } else if (num == 0) {
                System.out.printf("%nGoodbye!%n");
                break;
            } else {
                System.out.printf("%nThe first parameter should be a natural number or zero.%n%n");
            }
        }
    }
}
