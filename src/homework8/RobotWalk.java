package homework8;

class RobotWalk {
    private static Object lock = new Object();
    private static boolean isLeft = true;

    public static void main(String[] args) {
        Thread leftThread = new Thread(() -> walk("Left"));
        Thread rightThread = new Thread(() -> walk("Right"));

        leftThread.start();
        rightThread.start();
    }

    private static void walk(String leg) {
        for (int i = 0; i < 10; i++) {
            synchronized (lock) {
                try {
                    while ((leg.equals("Left") && !isLeft) || (leg.equals("Right") && isLeft)) {
                        lock.wait();
                    }
                    System.out.println(leg);
                    isLeft = !isLeft;
                    lock.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
