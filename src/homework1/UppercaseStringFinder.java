//bodlogo 4
package homework1;
import java.util.Scanner;

public class UppercaseStringFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ug hoorond zai awch temdegt mor oruulna uu ");
        String input = scanner.nextLine();

        String[] stringArray = input.split("\\s+");

        String result = findMostUpperWord(stringArray);
        System.out.println("Hamgiin olon tom usegtei ug: " + result);

        scanner.close();
    }

    public static String findMostUpperWord(String[] strings) {
        String result = null;
        int maxUppercaseCount = 0;

        for (String str : strings) {
            int uppercaseCount = countUppercaseLetters(str);

            if (uppercaseCount > maxUppercaseCount) {
                maxUppercaseCount = uppercaseCount;
                result = str;
            }
        }

        return result;
    }

    public static int countUppercaseLetters(String str) {
        int count = 0;

        for (char c : str.toCharArray()) {
            if (Character.isUpperCase(c)) {
                count++;
            }
        }

        return count;
    }

}
