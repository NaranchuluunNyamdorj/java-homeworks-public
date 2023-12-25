package homework8;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.*;

class ArrayStatistics implements Serializable {
    int[] array;
    private int min;
    private int max;

    public ArrayStatistics(int[] array) {
        this.array = array;
    }

    public void calculateMinMax() {
        min = array[0];
        max = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            } else if (array[i] > max) {
                max = array[i];
            }
        }
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}

public class FourIntegerArray {

    public static void main(String[] args) {
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(4);

            ArrayStatistics[] arrayStatistics = new ArrayStatistics[4];
            Future<?>[] futures = new Future[4];

            for (int i = 0; i < 4; i++) {
                arrayStatistics[i] = readArrayFromConsole(i);
            }

            for (int i = 0; i < 4; i++) {
                final int index = i;
                futures[i] = executorService.submit(() -> {
                    try {
                        writeArrayToBinaryFile(arrayStatistics[index], "array" + index + ".bin");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

            for (int i = 0; i < 4; i++) {
                futures[i].get();
            }

            for (int i = 0; i < 4; i++) {
                final int index = i;
                futures[i] = executorService.submit(() -> {
                    try {
                        readArrayFromBinaryFile("array" + index + ".bin");
                    } catch (IOException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

            for (int i = 0; i < 4; i++) {
                futures[i].get();
            }

            for (int i = 0; i < 4; i++) {
                int sum = calculateSum("array" + i + ".bin");
                System.out.println("Sum of array in array" + i + ".bin: " + sum);
            }

            executorService.shutdown();

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static ArrayStatistics readArrayFromConsole(int index) {
        int[] array = new int[5];
        System.out.println("Enter 5 values for Array " + index + ": ");
        for (int i = 0; i < 5; i++) {
            array[i] = readIntFromConsole("Value " + (i + 1) + ": ");
        }
        return new ArrayStatistics(array);
    }

    private static int readIntFromConsole(String message) {
        int value = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print(message);
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                value = Integer.parseInt(reader.readLine());
                validInput = true;
            } catch (NumberFormatException | IOException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
        return value;
    }

    private static void writeArrayToBinaryFile(ArrayStatistics arrayStatistics, String fileName) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(arrayStatistics);
            System.out.println("Array has been written to binary file: " + fileName);
        }
    }

    private static void readArrayFromBinaryFile(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            ArrayStatistics arrayStatistics = (ArrayStatistics) objectInputStream.readObject();
            arrayStatistics.calculateMinMax();
            System.out.println("Array from binary file " + fileName + ": Min = " + arrayStatistics.getMin() + ", Max = " + arrayStatistics.getMax());
        }
    }

    private static int calculateSum(String fileName) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            ArrayStatistics arrayStatistics = (ArrayStatistics) objectInputStream.readObject();
            return Arrays.stream(arrayStatistics.array).sum();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
