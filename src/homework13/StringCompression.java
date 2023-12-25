package homework13;

import java.util.stream.Collectors;

public class StringCompression {

    public static void main(String[] args) {
        String inputString = "AAAABCCCCCDDD";

        // Compression
        String compressedString = compressString(inputString);
        System.out.println("Compressed String: " + compressedString);

        // Decompression
        String decompressedString = decompressString(compressedString);
        System.out.println("Decompressed String: " + decompressedString);
    }

    public static String compressString(String input) {
        return input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .entrySet().stream()
                .map(entry -> entry.getValue() == 1 ?
                        String.valueOf(entry.getKey()) :
                        entry.getValue() + String.valueOf(entry.getKey()))
                .collect(Collectors.joining());
    }

    public static String decompressString(String compressed) {
        StringBuilder decompressed = new StringBuilder();

        for (int i = 0; i < compressed.length(); i++) {
            char currentChar = compressed.charAt(i);

            if (Character.isDigit(currentChar)) {
                int count = Character.getNumericValue(currentChar);
                char nextChar = compressed.charAt(i + 1);
                decompressed.append(String.valueOf(nextChar).repeat(Math.max(0, count)));
                i++;
            } else {
                decompressed.append(currentChar);
            }
        }
        return decompressed.toString();
    }
}
