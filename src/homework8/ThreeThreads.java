package homework8;

public class ThreeThreads {

    private static final Object lock = new Object();
    private static char currentChar = 'A';
    private static final int PRINT_COUNT = 100;

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> printAlphabet('A'));
        Thread thread2 = new Thread(() -> printAlphabet('B'));
        Thread thread3 = new Thread(() -> printAlphabet('C'));

        thread1.start();
        thread2.start();
        thread3.start();
    }

    private static void printAlphabet(char targetChar) {
        synchronized (lock) {
            for (int i = 0; i < PRINT_COUNT; i++) {
                while (currentChar != targetChar) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for (int j = 0; j < PRINT_COUNT; j++) {
                    System.out.print(targetChar);
                }

                currentChar = (char) ((currentChar - 'A' + 1) % 26 + 'A');

                lock.notifyAll();
            }
        }
    }
}
