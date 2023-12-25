//bodlogo 3
package homework1;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CountNonDuplicatedStr {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<String> allWords = new HashSet<>();

        System.out.println("Temdegt mur oruulna uu. stop commandaar zogsoono:");

        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("stop")) {
                scanner.close();
                break;
            }

            String[] words = input.split("\\s+");

            Collections.addAll(allWords, words);
        }

        System.out.println("Ylgaatai ugsiin too: " + allWords.size());

        scanner.close();
    }

}
