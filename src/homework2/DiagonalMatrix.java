package homework2;

import java.io.*;
import java.util.Random;

public class DiagonalMatrix {

    public static void main(String[] args) {
        int m = 3;
        int n = 4;
        int k = 5;

        int[][] matrix1 = generateMatrix(m, n, k);
        int[][] matrix2 = generateMatrixWithEqualDiagonal(m, n, k);

        saveMatrixToFile(matrix1, "matrix1.txt");
        saveMatrixToFile(matrix2, "matrix2.txt");

        printMatrix("Matrix 1:", matrix1);
        printMatrix("Matrix 2:", matrix2);

        printDiagonalElements("Diagonal Elements Matrix 1:", getDiagonalElements(matrix1));
        printDiagonalElements("Diagonal Elements Matrix 2:", getDiagonalElements(matrix2));
    }

    private static int[][] generateMatrix(int m, int n, int range) {
        int[][] matrix = new int[m][n];
        Random random = new Random();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextInt(range);
            }
        }

        return matrix;
    }

    private static int[][] generateMatrixWithEqualDiagonal(int m, int n, int value) {
        int[][] matrix = new int[m][n];

        for (int i = 0; i < Math.min(m, n); i++) {
            matrix[i][i] = value;
        }

        return matrix;
    }

    private static void saveMatrixToFile(int[][] matrix, String fileName) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            int m = matrix.length;
            int n = matrix[0].length;

            writer.println(m + " " + n);

            for (int[] ints : matrix) {
                for (int j = 0; j < n; j++) {
                    writer.print(ints[j] + " ");
                }
                writer.println();
            }

            System.out.println("Matrix has been saved to file: " + fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printMatrix(String message, int[][] matrix) {
        System.out.println(message);
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int[] getDiagonalElements(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int minDimension = Math.min(m, n);
        int[] diagonalElements = new int[minDimension];

        for (int i = 0; i < minDimension; i++) {
            diagonalElements[i] = matrix[i][i];
        }

        return diagonalElements;
    }

    private static void printDiagonalElements(String message, int[] diagonalElements) {
        System.out.println(message);
        for (int element : diagonalElements) {
            System.out.print(element + " ");
        }
        System.out.println();
        System.out.println();
    }
}
