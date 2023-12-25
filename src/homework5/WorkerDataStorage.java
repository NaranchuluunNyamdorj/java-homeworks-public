package homework5;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WorkerDataStorage {

    public static void main(String[] args) {
        List<Worker> workers = new ArrayList<>();
        workers.add(new Worker("Smith", "Manager", "Male", LocalDate.of(1972, 5, 15)));
        workers.add(new Worker("Johnson", "Engineer", "Female", LocalDate.of(1990, 8, 22)));
        workers.add(new Worker("Williams", "Analyst", "Male", LocalDate.of(1998, 2, 10)));
        saveWorkersToBinaryFile(workers, "worker_data.bin");

        List<Worker> loadedWorkers = loadWorkersFromBinaryFile("worker_data.bin");
        List<Worker> matureWorkers = filterMatureWorkers(loadedWorkers);
        displayWorkersInfoSortedByGender(matureWorkers);
    }

    private static void saveWorkersToBinaryFile(List<Worker> workers, String fileName) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(workers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Worker> loadWorkersFromBinaryFile(String fileName) {
        List<Worker> loadedWorkers = new ArrayList<>();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            loadedWorkers = (List<Worker>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return loadedWorkers;
    }

    private static List<Worker> filterMatureWorkers(List<Worker> workers) {
        return workers.stream()
                .filter(worker -> isMature(worker))
                .collect(Collectors.toList());
    }

    private static boolean isMature(Worker worker) {
        int currentYear = LocalDate.now().getYear();
        int workerAge = currentYear - worker.getDateOfBirth().getYear();

        return workerAge == 50 || worker.getDateOfBirth().plusYears(50).getYear() == currentYear;
    }

    private static void displayWorkersInfoSortedByGender(List<Worker> workers) {
        List<Worker> sortedWorkers = workers.stream()
                .sorted(Comparator.comparing(Worker::getGender))
                .toList();

        System.out.println("Tetgevert garah ajiltnuud: ");
        for (Worker worker : sortedWorkers) {
            System.out.println(worker);
        }
    }
}
