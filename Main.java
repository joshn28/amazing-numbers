package numbers;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
public class Main {

    static final String PROPS = "even, odd, buzz, duck, palindromic, gapful, spy, square, sunny, jumping";

    public static String[] getProperties(String[] inputs) {
        String[] properties = new String[inputs.length - 2];
        int start = 2;

        for (int i = 0; i < properties.length; i++) {
            properties[i] = inputs[start + i].toLowerCase();
        }

        return properties;
    }

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

    public static String getNumProps(long num) {

        return num + " is " + (num % 2 == 0 ? "even" : "odd") +
                (num % 7 == 0 || num % 10 == 7 ? ", buzz" : "") +
                (Long.toString(num).contains("0") ? ", duck" : "") +
                (isPalindromic(num) ? ", palindromic" : "") +
                (isGapful(num) ? ", gapful" : "") +
                (isSpy(num) ? ", spy" : "") +
                (isSquare(num) ? ", square": "") +
                (isSquare(num + 1) ? ", sunny" : "") +
                (isJumping(num) ? ", jumping" : "");
    }

    public static Boolean propsAreMutuallyExclusive(String... properties) {
        if (properties.length > 1) {
            if (Arrays.toString(properties).contains("even") && Arrays.toString(properties).contains("odd")) {
                return true;
            } else if (Arrays.toString(properties).contains("duck")
                    && Arrays.toString(properties).contains("spy")) {
                return true;
            } else return Arrays.toString(properties).contains("sunny")
                    && Arrays.toString(properties).contains("square");
        }

        return false;
    }

    public static ArrayList<String> getInvalidProps(String... properties) {
        ArrayList<String> invalidProps = new ArrayList<>();

        for (String prop: properties) {
            if (!PROPS.contains(prop)) {
                invalidProps.add(prop);
            }
        }

        return invalidProps;
    }

    public static Boolean propsAreValid(String... properties) {
        ArrayList<String> invalidProps = getInvalidProps(properties);

        if (invalidProps.size() == 1) {
            System.out.printf("""
                    The property %s is wrong.
                    Available properties: %s
                    %n""", invalidProps, PROPS);
            return false;
        } else if (invalidProps.size() > 1) {
            System.out.printf("""
                    The properties %s are wrong.
                    Available properties: %s
                    %n""", invalidProps, PROPS);
            return false;
        }

        if (propsAreMutuallyExclusive(properties)) {
            System.out.printf("""
                    The request contains mutually exclusive properties: %s
                    There are no numbers with these properties.
                    %n""", Arrays.toString(properties));
            return false;
        }

        return true;
    }

    public static Boolean numHasProps(long num, String... properties) {
        for (String property : properties) {
            if (!getNumProps(num).contains(property.toLowerCase())) {
                return false;
            }
        }

        return true;
    }

    public static void showNumPropertiesList(long start, long end) {
        for (long i = 0; i < end; i++) {
            System.out.println(getNumProps(start++));
        }
        System.out.println();
    }

    public static void showNumPropertiesList(long start, long end, String... properties) {
        if (!propsAreValid(properties)) {
            return;
        }

        for (long i = 0; i < end;) {
            if (numHasProps(start, properties)) {
                System.out.println(getNumProps(start));
                i++;
            }
            start++;
        }
        System.out.println();
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
        boolean jumping = isJumping(num);

        System.out.printf("""
                %nProperties of %,d
                        buzz: %b
                        duck: %b
                 palindromic: %b
                      gapful: %b
                         spy: %b
                      square: %b
                       sunny: %b
                     jumping: %b
                        even: %b
                         odd: %b
                %n""", num, buzz, duck, palindromic, gapful, spy, square, sunny, jumping, even, odd);
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
                } else if (inputs.length > 2) {
                    String[] properties = getProperties(inputs);
                    System.out.println();
                    showNumPropertiesList(firstNum, secondNum, properties);
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
