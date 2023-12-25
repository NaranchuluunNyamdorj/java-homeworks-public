package homework2;

import java.io.*;

public class EvenToOdd {

    public static void main(String[] args) {
        saveNumbersToFile("numbers.txt");
        separateNumbers("numbers.txt", "ordered_numbers.txt");
    }

    private static void saveNumbersToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = -10; i <= 10; i++) {
                writer.write(i + "\n");
            }
            System.out.println("Numbers have been saved to file: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void separateNumbers(String inputFileName, String outputFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {

            StringBuilder evenNumbers = new StringBuilder();
            StringBuilder oddNumbers = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                int number = Integer.parseInt(line);
                if (number % 2 == 0) {
                    evenNumbers.append(number).append("\n");
                } else {
                    oddNumbers.append(number).append("\n");
                }
            }

            writer.write(evenNumbers.toString());
            writer.write(oddNumbers.toString());

            System.out.println(outputFileName);

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
