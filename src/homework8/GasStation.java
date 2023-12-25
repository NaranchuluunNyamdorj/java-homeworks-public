package homework8;

import java.util.Scanner;

public class GasStation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of devices: ");
        int numDevices = scanner.nextInt();
        System.out.print("Enter the average refueling time (in minutes): ");
        int avgRefuelTime = scanner.nextInt();
        System.out.print("Enter the maintenance interval (in minutes): ");
        int maintenanceInterval = scanner.nextInt();

        int[] maintenanceTimes = new int[numDevices];
        for (int i = 0; i < numDevices; i++) {
            System.out.print("Enter the maintenance time for device " + (i + 1) + ": ");
            maintenanceTimes[i] = scanner.nextInt();
        }

        Thread[] threads = new Thread[numDevices + 1];
        for (int i = 0; i < numDevices; i++) {
            threads[i] = new Thread(new DeviceThread(i + 1, avgRefuelTime, maintenanceTimes[i], maintenanceInterval));
            threads[i].start();
        }

        threads[numDevices] = new Thread(new MaintenanceThread(maintenanceInterval, threads));
        threads[numDevices].start();

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int totalCarsServedAll = 0;
        int[] carsServedPerDevice = new int[numDevices];
        for (int i = 0; i < numDevices; i++) {
            totalCarsServedAll += carsServedPerDevice[i];
        }

        System.out.println("\na) Data entered manually:");
        System.out.println("Number of devices: " + numDevices);
        System.out.println("Average refueling time: " + avgRefuelTime + " minutes");
        System.out.println("Maintenance interval: " + maintenanceInterval + " minutes");
        System.out.println("Maintenance times:");
        for (int i = 0; i < numDevices; i++) {
            System.out.println("Device " + (i + 1) + ": " + maintenanceTimes[i] + " minutes");
        }
        System.out.println("\nb) Number of cars served by all devices in 4 hours: " + totalCarsServedAll);
        System.out.println("\nc) Number of cars served by each device in 4 hours: ");
        for (int i = 0; i < numDevices; i++) {
            System.out.println("Device " + (i + 1) + ": " + carsServedPerDevice[i]);
        }
    }
}

class DeviceThread implements Runnable {
    private int deviceId;
    private int avgRefuelTime;
    private int maintenanceTime;
    private int maintenanceInterval;
    private int carsServed = 0;

    public DeviceThread(int deviceId, int avgRefuelTime, int maintenanceTime, int maintenanceInterval) {
        this.deviceId = deviceId;
        this.avgRefuelTime = avgRefuelTime;
        this.maintenanceTime = maintenanceTime;
        this.maintenanceInterval = maintenanceInterval;
    }

    @Override
    public void run() {
        int currentTime = 0;
        while (currentTime < 4 * 60) {
            try {
                Thread.sleep(avgRefuelTime * 60 * 1000);
            } catch (InterruptedException e) {
            }
            carsServed++;
            currentTime += avgRefuelTime;

            synchronized (this) {
                try {
                    wait(maintenanceInterval * 60 * 1000 - currentTime % (maintenanceInterval * 60 * 1000));
                } catch (InterruptedException e) {
                }
            }

            try {
                Thread.sleep(maintenanceTime * 60 * 1000);
            } catch (InterruptedException e) {
            }
            currentTime += maintenanceTime;
        }
    }
}

class MaintenanceThread implements Runnable {
    private int maintenanceInterval;
    private Thread[] deviceThreads;

    public MaintenanceThread(int maintenanceInterval, Thread[] deviceThreads) {
        this.maintenanceInterval = maintenanceInterval;
        this.deviceThreads = deviceThreads;
    }

    @Override
    public void run() {
        while (true) {
            for (Thread thread : deviceThreads) {
                synchronized (thread) {
                    try {
                        thread.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            for (Thread thread : deviceThreads) {
                synchronized (thread) {
                    thread.notify();
                }
            }

            try {
                Thread.sleep(maintenanceInterval * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
