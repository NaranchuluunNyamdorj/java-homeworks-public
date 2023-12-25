//bodlogo 2
package homework1;
import java.util.Scanner;

public class NonRepeatedChar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ug programm n temdegt murund dawhtsaagui usegiig butsaana.");
        System.out.print("Temdegt mur oruulna uu: ");

        String input = scanner.nextLine();
        if(!input.isBlank()){
            if(findNonRepeatedCharacters(input).isEmpty()){
                System.out.println("Dawhtsaagui temdegt baihgui");
            }else{
                System.out.println("Dawhtsaagui temdegtuud: " + findNonRepeatedCharacters(input));
            }
        }else{
            System.out.println("Utga oruulna uu.");
        }

        scanner.close();
    }

    public static String findNonRepeatedCharacters(String input) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            if (input.indexOf(currentChar) == input.lastIndexOf(currentChar)) {
                result.append(currentChar);
            }
        }

        return result.toString();
    }

}
