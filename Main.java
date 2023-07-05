package numbers;

import java.util.Scanner;
public class Main {

    public static void showNumProperties(int num) {
        boolean even = num % 2 == 0;
        boolean odd = num % 2 != 0;
        boolean buzz = num % 7 == 0 || num % 10 == 7;
        boolean duck = Integer.toString(num).contains("0");;

        System.out.printf("""
                Properties of %d
                    even: %b
                    odd: %b
                    buzz: %b
                    duck: %b
                """, num, even, odd, buzz, duck);
    }

    public static void main(String[] args) {
        System.out.println("Enter a natural number:");

        Scanner scanner = new Scanner(System.in);

        int num = scanner.nextInt();
        if (num > 0) {
            showNumProperties(num);
        } else {
            System.out.println("This number is not natural!");
        }
    }
}
