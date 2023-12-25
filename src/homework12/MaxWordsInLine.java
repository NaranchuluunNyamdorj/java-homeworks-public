package homework12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MaxWordsInLine {

    public static void main(String[] args) {
        List<LineInfo> linesWithMostWords = findLinesWithMostWords(Paths.get("text.txt"));
        linesWithMostWords.forEach(System.out::println);
    }

    private static List<LineInfo> findLinesWithMostWords(Path filePath) {
        try {
            try (Stream<String> lines = Files.lines(filePath)) {
                int maxWords = lines
                        .mapToInt(line -> countWords(line))
                        .max()
                        .orElse(0);


                return Files.lines(filePath)
                        .filter(line -> countWords(line) == maxWords)
                        .map(line -> new LineInfo(getLineNumber(filePath, line), line, maxWords))
                        .collect(Collectors.toList());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static int countWords(String line) {
        return (int) Stream.of(line.split("\\s+"))
                .filter(word -> !word.isEmpty())
                .count();
    }

    private static int getLineNumber(Path filePath, String targetLine) {
        try {
            List<String> allLines = Files.readAllLines(filePath);

            return IntStream.range(0, allLines.size())
                    .filter(i -> allLines.get(i).equals(targetLine))
                    .findFirst()
                    .orElse(0) + 1;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }


    static class LineInfo {
        private final int lineNumber;
        private final String line;
        private final int wordCount;

        public LineInfo(int lineNumber, String line, int wordCount) {
            this.lineNumber = lineNumber;
            this.line = line;
            this.wordCount = wordCount;
        }

        @Override
        public String toString() {
            return "Line " + lineNumber + ": " + line + " (Word Count: " + wordCount + ")";
        }
    }
}
