package java_practice;

public class JavaPractice1 {

    public static void main(String[] args) {
        int num1 = 10;
        int num2 = 3;
        practiceMathOperators(num1, num2);
        System.out.println("\n");
        practiceLogicalOperators(num1, num2);
        System.out.println("\n");

        practiceOverflow();
        System.out.println("\n");
        practiceOperationsIntAndDouble();
    }

    public static void practiceMathOperators(int num1, int num2) {

        System.out.println("Practice for math operators");
        int sum = num1 + num2;
        int difference = num1 - num2;
        int multiplication = num1 * num2;
        int division = num1 / num2;
        int remainder = num1 % num2;

        System.out.println("Sum of numbers: " + sum);
        System.out.println("Difference of numbers: " + difference);
        System.out.println("Multiplication of numbers: " + multiplication);
        System.out.println("Division of numbers: " + division);
        System.out.println("Remainder of numbers: " + remainder);
    }

    public static void practiceLogicalOperators(int num1, int num2) {
        System.out.println("Practice for logical operators");
        System.out.println(num1 > 5 && num2 > 2);
        System.out.println(num1 < num2 || num2 >=3);
        System.out.println(!(num1 > num2));
    }

    public static void practiceOverflow() {
        System.out.println("Practice overflow after operations with numbers");
        int minIntOverflow = Integer.MIN_VALUE - 1;
        System.out.println("Negative integer overflow result: " + minIntOverflow);
        int maxIntOverflow = Integer.MAX_VALUE + 1;
        System.out.println("Positive integer overflow result: " + maxIntOverflow);

        long minLongOverflow = Long.MIN_VALUE - 1;
        System.out.println("Negative long overflow result: " + minLongOverflow);
        long maxLongOverflow = Long.MAX_VALUE + 1;
        System.out.println("Positive long overflow result: " + maxLongOverflow);

        float minFloatOverflow = Float.MIN_VALUE - 1;
        System.out.println("Minimal float value: " + Float.MIN_VALUE);
        System.out.println("Negative float overflow result: " + minFloatOverflow);
        float maxFloatOverflow = Float.MAX_VALUE + 1;
        System.out.println("Maximum float value: " + Float.MAX_VALUE);
        System.out.println("Positive float overflow result: " + maxFloatOverflow);

        double minDoubleOverflow = Double.MIN_VALUE - 1;
        System.out.println("Minimal double value: " + Double.MIN_VALUE);
        System.out.println("Negative double overflow result: " + minDoubleOverflow);
        double maxDoubleOverflow = Double.MAX_VALUE + 1;
        System.out.println("Maximum double value: " + Double.MAX_VALUE);
        System.out.println("Positive double overflow result: " + maxDoubleOverflow);
    }

    public static void practiceOperationsIntAndDouble() {
        System.out.println("Expected : 6.6, Actual : " + 1.1 * 3 * 2.0);
        System.out.println("Expected : 1.65, Actual : " + 1.1 * 3 / 2.0);
        System.out.println("Expected : 2.0, Actual : " + (10 - 5.0 - 1.1 - 2.9) * 2);
        System.out.println(5 / 2.2);
        System.out.println((int) (5 / 2.2));
    }
}
