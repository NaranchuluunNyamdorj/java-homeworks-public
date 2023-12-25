package homework11;

import java.util.Arrays;
import java.util.List;

public class FunctionalProgramming {
    public static void main(String[] args) {
        String text = "Hello Functional Programming with lambda and functional Interface";

        int count = countLowerCaseCharacters(text);
        System.out.println("Жижиг үсгийн тоо: " + count);

        List<String> lowerCaseWords = getLowerCaseWords(text);
        System.out.println("Жижиг үсгээр бичсэн үгүүд: " + lowerCaseWords);
    }

    @FunctionalInterface
    interface CharacterFilter {
        boolean filter(char c);
    }

    @FunctionalInterface
    interface WordFilter {
        boolean filter(String word);
    }

    private static int countLowerCaseCharacters(String text) {
        return countCharacters(text, c -> Character.isLowerCase(c));
    }

    private static List<String> getLowerCaseWords(String text) {
        return getWords(text, word -> word.matches("^[a-z]+$"));
    }

    private static int countCharacters(String text, CharacterFilter filter) {
        int count = 0;
        for (char c : text.toCharArray()) {
            if (filter.filter(c)) {
                count++;
            }
        }
        return count;
    }

    private static List<String> getWords(String text, WordFilter filter) {
        String[] words = text.split("\\s+"); // Split by whitespace
        return Arrays.stream(words).filter(word -> filter.filter(word)).toList();
    }
}
