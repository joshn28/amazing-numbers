package numbers;

import java.util.Scanner;
public class Main {
    public static void isBuzzNumber(int num) {
        if (num % 7 == 0 && num % 10 == 7) {
            System.out.println("It is a Buzz number.");
            System.out.printf("Explanation:%n%d is divisible by 7 and ends with 7.", num);
        } else if (num % 7 == 0) {
            System.out.println("It is a Buzz number.");
            System.out.printf("Explanation:%n%d is divisible by 7.", num);
        } else if (num % 10 == 7) {
            System.out.println("It is a Buzz number.");
            System.out.printf("Explanation:%n%d ends with 7.", num);
        } else {
            System.out.println("It is not a Buzz number.");
            System.out.printf("Explanation:%n%d is neither divisible by 7 nor does it end with 7.", num);
        }
    }

    public static void main(String[] args) {
        System.out.println("Enter a natural number:");

        Scanner scanner = new Scanner(System.in);

        int num = scanner.nextInt();
        if (num > 0) {
            String parity = num % 2 == 0 ? "This number is Even." : "This number is Odd.";
            System.out.println(parity);
            isBuzzNumber(num);
        } else {
            System.out.println("This number is not natural!");
        }
    }
}
