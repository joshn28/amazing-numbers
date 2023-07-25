package numbers;

import java.util.Scanner;
import java.util.Arrays;
public class Main {

    public static boolean isSpy(long num) {
        String[] digits = String.valueOf(num).split("");

        long sum = 0;
        long product = 1;

        for (String s : digits) {
            long digit = Long.parseLong(s);

            sum += digit;
            product *= digit;
        }

        return sum == product;
    }

    public static boolean isGapful(long num) {
        String strNum = Long.toString(num);

        if (strNum.length() < 3) {
            return false;
        } else {
            String firstDigit = String.valueOf(strNum.charAt(0));
            String lastDigit = String.valueOf(strNum.charAt(strNum.length() - 1));

            int combined = Integer.parseInt(firstDigit + lastDigit);

            return num % combined == 0;
        }
    }

    public static boolean isPalindromic(long num) {
        String strNum = Long.toString(num);
        StringBuilder reversedNum = new StringBuilder(strNum).reverse();

        return strNum.contentEquals(reversedNum);
    }

    public static void printInfo(long num) {
        StringBuilder info = new StringBuilder(num + " is ");

        info.append(num % 2 == 0 ? "even" : "odd");
        info.append(num % 7 == 0 || num % 10 == 7 ? ", buzz" : "");
        info.append(Long.toString(num).contains("0") ? ", duck" : "");
        info.append(isPalindromic(num) ? ", palindromic" : "");
        info.append(isGapful(num) ? ", gapful" : "");
        info.append(isSpy(num) ? ", spy" : "");

        System.out.println(info);
    }

    public static Boolean numHasProp(long num, String property) {
        switch (property) {
            case "BUZZ":
                return num % 7 == 0 || num % 10 == 7;
            case "DUCK":
                return Long.toString(num).contains("0");
            case "PALINDROMIC":
                return isPalindromic(num);
            case "GAPFUL":
                return isGapful(num);
            case "SPY":
                return isSpy(num);
            case "EVEN":
                return num % 2 == 0;
            case "ODD":
                return num % 2 != 0;
            default:
                return false;
        }
    }

    public static void showNumPropertiesList(long start, long end) {
        for (long i = 0; i < end; i++) {
            printInfo(start++);
        }
        System.out.println();
    }

    public static void showNumPropertiesList(long start, long end, String property) {
        String[] properties = {"BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "EVEN", "ODD"};
        property = property.toUpperCase();

        if (Arrays.asList(properties).contains(property)) {
            for (long i = 0; i < end;) {
                if (numHasProp(start, property)) {
                    printInfo(start);
                    i++;
                }
                start++;
            }
            System.out.println();
        } else {
            System.out.printf("""
                    %nThe property [%s] is wrong.
                    Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD]
                    %n""", property);
        }
    }

    public static void showNumProperties(long num) {
        boolean even = num % 2 == 0;
        boolean odd = num % 2 != 0;
        boolean buzz = num % 7 == 0 || num % 10 == 7;
        boolean duck = Long.toString(num).contains("0");
        boolean palindromic = isPalindromic(num);
        boolean gapful = isGapful(num);
        boolean spy = isSpy(num);

        System.out.printf("""
                %nProperties of %,d
                        buzz: %b
                        duck: %b
                 palindromic: %b
                      gapful: %b
                         spy: %b
                        even: %b
                         odd: %b
                %n""", num, buzz, duck, palindromic, gapful, spy, even, odd);
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!");
        System.out.printf("""
                %nSupported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be printed;
                - two natural numbers and a property to search for;
                - separate the parameters with one space;
                - enter 0 to exit.
                %n""");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a request: ");

            String[] inputs = scanner.nextLine().split(" ");

            if (inputs.length > 1) {
                long firstNum = Long.parseLong(inputs[0]);
                long secondNum = Long.parseLong(inputs[1]);

                if (firstNum < 0) {
                    System.out.printf("%nThe first parameter should be a natural number or zero.%n%n");
                } else if (secondNum < 0) {
                    System.out.printf("%nThe second parameter should be a natural number.%n%n");
                } else if (inputs.length == 3) {
                    String property = inputs[2];
                    System.out.println();
                    showNumPropertiesList(firstNum, secondNum, property);
                } else {
                    System.out.println();
                    showNumPropertiesList(firstNum, secondNum);
                }
            } else {
                if (!inputs[0].matches("\\d+")) {
                    System.out.printf("%nThe first parameter should be a natural number or zero.%n%n");
                    continue;
                }

                long num = Long.parseLong(inputs[0]);

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
}
