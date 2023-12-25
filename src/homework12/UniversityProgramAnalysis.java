package homework12;

import java.util.*;
import java.util.stream.Collectors;

class Student {
    String name, major, province, city;
    int entranceYear;

    public Student(String name, String major, String province, String city, int entranceYear) {
        this.name = name;
        this.major = major;
        this.province = province;
        this.city = city;
        this.entranceYear = entranceYear;
    }

    public String getName() { return name; }
    public String getMajor() { return major; }
    public String getProvince() { return province; }
    public String getCity() { return city; }
    public int getEntranceYear() { return entranceYear; }

    public void setName(String name) { this.name = name; }
    public void setMajor(String major) { this.major = major; }
    public void setProvince(String province) { this.province = province; }
    public void setCity(String city) { this.city = city; }
    public void setEntranceYear(int entranceYear) { this.entranceYear = entranceYear; }

    @Override
    public String toString() {
        return "Student{name='" + name + "', major='" + major + "', province='" + province + "', city='" + city + "', entranceYear=" + entranceYear + "}";
    }
}

public class UniversityProgramAnalysis {
    public static void main(String[] args) {
        List<Student> students = generateRandomStudentData(100);

        Map<String, Long> majorCountMap = students.stream()
                .collect(Collectors.groupingBy(Student::getMajor, Collectors.counting()));

        System.out.println("Major-wise Student Count:");
        majorCountMap.forEach((major, count) -> System.out.println(major + ": " + count));

        Map<String, Map<String, Long>> majorProvinceCountMap = students.stream()
                .collect(Collectors.groupingBy(Student::getMajor,
                        Collectors.groupingBy(Student::getProvince, Collectors.counting())));

        System.out.println("\nMajor-wise Student Count by Province:");
        majorProvinceCountMap.forEach((major, provinceCountMap) -> {
            System.out.println(major + ":");
            provinceCountMap.forEach((province, count) -> System.out.println("\t" + province + ": " + count));
        });

        Map<Integer, Long> entranceYearCountMap = students.stream()
                .collect(Collectors.groupingBy(Student::getEntranceYear, Collectors.counting()));

        int mostEntranceYear = entranceYearCountMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(-1);

        System.out.println("\nYear with the Most Entrances: " + mostEntranceYear);
    }

    private static List<Student> generateRandomStudentData(int numStudents) {
        Random random = new Random();
        List<String> majors = Arrays.asList("Computer Science", "Software Engineering", "Information Technology", "Information System");
        List<String> provinces = Arrays.asList("Province1", "Province2", "Province3", "Province4", "Ulaanbaatar");

        List<Student> students = new ArrayList<>();
        for (int i = 0; i < numStudents; i++) {
            String name = "Student" + (i + 1);
            String major = majors.get(random.nextInt(majors.size()));
            String province = provinces.get(random.nextInt(provinces.size()));
            String city = (province.equals("Ulaanbaatar")) ? "Ulaanbaatar" : "City" + (random.nextInt(10) + 1);
            int entranceYear = 2020 + random.nextInt(5);

            students.add(new Student(name, major, province, city, entranceYear));
        }

        return students;
    }
}
