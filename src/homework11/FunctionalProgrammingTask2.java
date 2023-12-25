package homework11;

import java.util.Arrays;
import java.util.Comparator;

public class FunctionalProgrammingTask2 {
    public static void main(String[] args) {
        String[] array1 = {"hello", "spoon", "cat"};
        String[] array2 = {"I", "love", "to", "music"};
        String[] array3 = {"world", "job", "july"};

        String[][] arrays = {array1, array2, array3};
        Arrays.sort(arrays, Comparator.comparingInt(array -> array.length));

        System.out.println("Arrays sorted by the number of words in ascending order:");
        printArrays(arrays);

        String[] minArray = Arrays.stream(arrays).min(Comparator.comparingInt(array -> array.length)).orElse(null);

        System.out.println("Array with the smallest number of words: " + Arrays.toString(minArray));

        String[] maxArray = Arrays.stream(arrays).max(Comparator.comparingInt(array -> array.length)).orElse(null);

        System.out.println("Array with the largest number of words: " + Arrays.toString(maxArray));

        Arrays.sort(arrays, Comparator.comparingInt(array -> Arrays.stream(array).mapToInt(String::length).sum()));

        System.out.println("Arrays sorted by the total number of characters in each array:");
        printArrays(arrays);
    }

    private static void printArrays(String[][] arrays) {
        for (String[] array : arrays) {
            System.out.println(Arrays.toString(array));
        }
    }
}
