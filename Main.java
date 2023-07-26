package numbers;

import java.util.Scanner;
import java.util.Arrays;
public class Main {

    static final String[] PROPERTIES = {
            "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "EVEN", "ODD", "SUNNY", "SQUARE", "JUMPING"
    };

    public static boolean isJumping(long num) {
        String[] numbers = Long.toString(num).split("");

        for (int i = 0; i < numbers.length - 1; i++) {
            long num1 = Long.parseLong(numbers[i]);
            long num2 = Long.parseLong(numbers[i+1]);

            if (Math.abs(num1 - num2) != 1) {
                return false;
            }
        }

        return true;
    }

    public static boolean isSquare(long num) {
        return Math.sqrt(num) == (int) Math.sqrt(num);
    }

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

        String info = num + " is " + (num % 2 == 0 ? "even" : "odd") +
                (num % 7 == 0 || num % 10 == 7 ? ", buzz" : "") +
                (Long.toString(num).contains("0") ? ", duck" : "") +
                (isPalindromic(num) ? ", palindromic" : "") +
                (isGapful(num) ? ", gapful" : "") +
                (isSpy(num) ? ", spy" : "") +
                (isSquare(num) ? ", square": "") +
                (isSquare(num + 1) ? ", sunny" : "");

        System.out.println(info);
    }

    public static Boolean propsAreMutuallyExclusive(String property1, String property2) {
        property1 = property1.toUpperCase();
        property2 = property2.toUpperCase();

        Boolean firstCase = property1.equals("EVEN") && property2.equals("ODD")
                            || property1.equals("ODD") && property2.equals("EVEN");

        Boolean secondCase = property1.equals("DUCK") && property2.equals("SPY")
                             || property1.equals("SPY") && property2.equals("DUCK");

        Boolean thirdCase = property1.equals("SUNNY") && property2.equals("SQUARE")
                            || property1.equals("SQUARE") && property2.equals("SUNNY");

        return firstCase || secondCase || thirdCase;
    }

    public static Boolean numHasProp(long num, String property) {
        return switch (property) {
            case "BUZZ" -> num % 7 == 0 || num % 10 == 7;
            case "DUCK" -> Long.toString(num).contains("0");
            case "PALINDROMIC" -> isPalindromic(num);
            case "GAPFUL" -> isGapful(num);
            case "SPY" -> isSpy(num);
            case "SQUARE" -> isSquare(num);
            case "SUNNY" -> isSquare(num + 1);
            case "EVEN" -> num % 2 == 0;
            case "ODD" -> num % 2 != 0;
            default -> false;
        };
    }

    public static void showNumPropertiesList(long start, long end) {
        for (long i = 0; i < end; i++) {
            printInfo(start++);
        }
        System.out.println();
    }

    public static void showNumPropertiesList(long start, long end, String property) {
        property = property.toUpperCase();

        if (Arrays.asList(PROPERTIES).contains(property)) {
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
                    Available properties: %s
                    %n""", property, Arrays.toString(PROPERTIES));
        }
    }

    public static void showNumPropertiesList(long start, long end, String property1, String property2) {
        property1 = property1.toUpperCase();
        property2 = property2.toUpperCase();

        if (Arrays.asList(PROPERTIES).contains(property1) && Arrays.asList(PROPERTIES).contains(property2)) {
            if (property1.equals(property2)) {
                showNumPropertiesList(start, end, property1);
            } else if (!propsAreMutuallyExclusive(property1, property2)) {
                for (long i = 0; i < end;) {
                    if (numHasProp(start, property1) && numHasProp(start, property2)) {
                        printInfo(start);
                        i++;
                    }
                    start++;
                }
                System.out.println();
            } else {
                System.out.printf("""
                        The request contains mutually exclusive properties: [%s, %s]
                        There are no numbers with these properties.
                        %n""", property1, property2);
            }
        } else if (!Arrays.asList(PROPERTIES).contains(property1) && !Arrays.asList(PROPERTIES).contains(property2)){
            System.out.printf("""
                    The properties [%s, %s] are wrong.
                    Available properties: %s
                    %n""", property1, property2, Arrays.toString(PROPERTIES));
        } else {
            System.out.printf("""
                    The property [%s, %s] is wrong.
                    Available properties: %s
                    %n""", property1, property2, Arrays.toString(PROPERTIES));
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
        boolean square = isSquare(num);
        boolean sunny = isSquare(num + 1);

        System.out.printf("""
                %nProperties of %,d
                        buzz: %b
                        duck: %b
                 palindromic: %b
                      gapful: %b
                         spy: %b
                      square: %b
                       sunny: %b
                        even: %b
                         odd: %b
                %n""", num, buzz, duck, palindromic, gapful, spy, square, sunny, even, odd);
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!");
        System.out.printf("""
                %nSupported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be processed;
                - two natural numbers and a property to search for;
                - two natural numbers and two properties to search for;
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
                } else if (inputs.length == 4) {
                    String property1 = inputs[2];
                    String property2 = inputs[3];
                    System.out.println();
                    showNumPropertiesList(firstNum, secondNum, property1, property2);
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
