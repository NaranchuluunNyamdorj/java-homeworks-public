package homework5;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class StudentAnalysis {
    public static void main(String[] args) {
        try (FileInputStream fileInputStream = new FileInputStream("student_data.bin");
             DataInputStream dataInputStream = new DataInputStream(fileInputStream)) {

            ArrayList<Student> students = new ArrayList<>();

            while (dataInputStream.available() > 0) {
                students.add(new Student(dataInputStream.readUTF(), dataInputStream.readUTF(), dataInputStream.readUTF()));
            }

            boolean hasSameLastNameAcrossSchool = hasSameLastNameAcrossSchool(students);
            boolean hasSameLastNameInParallelClasses = hasSameLastNameInParallelClasses(students);
            boolean hasSameLastNameInClass = hasSameLastNameInClass(students);

            List<String> classesWithMoreThan35Students = getClassesWithMoreThan35Students(students);
            int difference = getDifferenceInStudentCounts(students);

            ArrayList<Student> ninthGradeStudents = getStudentsByGrade(students, 9);
            ArrayList<Student> tenthGradeStudents = getStudentsByGrade(students, 10);

            saveStudentsToBinaryFile(ninthGradeStudents, "9thGradeStudents.bin");
            saveStudentsToBinaryFile(tenthGradeStudents, "10thGradeStudents.bin");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Student> getStudentsByGrade(List<Student> students, int targetGrade) {
        return students.stream()
                .filter(student -> student.getGrade() == targetGrade)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private static boolean hasSameLastNameAcrossSchool(List<Student> students) {
        Set<String> uniqueLastNames = new HashSet<>();
        return students.stream().anyMatch(student -> !uniqueLastNames.add(student.getLastName()));
    }

    private static boolean hasSameLastNameInParallelClasses(List<Student> students) {
        Map<String, Set<String>> classLastNamesMap = new HashMap<>();
        return students.stream().anyMatch(student -> {
            String className = student.getClassName();
            Set<String> lastNamesInClass = classLastNamesMap.computeIfAbsent(className, k -> new HashSet<>());
            return !lastNamesInClass.add(student.getLastName());
        });
    }

    private static boolean hasSameLastNameInClass(List<Student> students) {
        Set<String> lastNamesInClass = new HashSet<>();
        return students.stream()
                .filter(student -> student.getClassName().equals("6A"))
                .anyMatch(student -> !lastNamesInClass.add(student.getLastName()));
    }

    private static List<String> getClassesWithMoreThan35Students(List<Student> students) {
        Map<String, Long> classCounts = students.stream()
                .collect(Collectors.groupingBy(Student::getClassName, Collectors.counting()));

        return classCounts.entrySet().stream()
                .filter(entry -> entry.getValue() > 35)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private static int getDifferenceInStudentCounts(List<Student> students) {
        long countGrade1 = students.stream().filter(student -> student.getGrade() == 8).count();
        long countGrade2 = students.stream().filter(student -> student.getGrade() == 10).count();
        return Math.abs((int) (countGrade1 - countGrade2));
    }

    private static void saveStudentsToBinaryFile(List<Student> students, String fileName) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream)) {

            for (Student student : students) {
                dataOutputStream.writeUTF(student.getFirstName());
                dataOutputStream.writeUTF(student.getLastName());
                dataOutputStream.writeUTF(student.getClassName());
            }
        }
    }

    static class Student {
        private String firstName, lastName, className;
        private int grade;

        public Student(String firstName, String lastName, String className) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.className = className;
            this.grade = Integer.parseInt(className.substring(0, 1));
        }

        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
        public String getClassName() { return className; }
        public int getGrade() { return grade; }
    }
}
