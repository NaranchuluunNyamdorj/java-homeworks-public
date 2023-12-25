package homework13;

import java.util.stream.IntStream;

public class SmallestProductOfDigits {

    public static void main(String[] args) {
        int inputNumber = 90;

        int result = findSmallestProductOfDigits(inputNumber);
        System.out.println("Result: " + result);
    }

    public static int findSmallestProductOfDigits(int n) {
        return IntStream.rangeClosed(1, 1000000000)
                .filter(i -> getProductOfDigits(i) == n)
                .findFirst()
                .orElse(-1);
    }

    private static int getProductOfDigits(int number) {
        return String.valueOf(number)
                .chars()
                .map(Character::getNumericValue)
                .reduce(1, (a, b) -> a * b);
    }
}
