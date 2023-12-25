//bodlogo 1
package homework1;
import java.util.Scanner;

public class StringIntoEquation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Useg, too bolon busad temdegtuudees butsen temdegt mur oruulhad temdegt murd aguulagdsan toonuudniig nemdeg program");
        System.out.print("Temdegt mur orulna uu: ");
        String input = scanner.nextLine();

        String equation = equationBuilder(input);

        System.out.println("Toonuudiin niilber: " + equation);
        scanner.close();
    }

    public static String equationBuilder(String input) {
        StringBuilder equation = new StringBuilder();
        int digitSum = 0;
        boolean firstDigit = true;

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            if (Character.isDigit(currentChar)) {
                if (!firstDigit) {
                    equation.append(" + ");
                } else {
                    firstDigit = false;
                }

                equation.append(currentChar);
                digitSum += Character.getNumericValue(currentChar);
            }
        }

        equation.append(" = ").append(digitSum);

        return equation.toString();
    }

}
