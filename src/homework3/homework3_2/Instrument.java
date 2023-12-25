package homework3.homework3_2;

public interface Instrument {

    public abstract void play();
    public abstract void feature();

    public static void main(String[] args) {
        Uleever u = new Uleever();
        Tsohivor ts = new Tsohivor();
        Utsan s = new Utsan();
        u.play();
        ts.play();
        s.play();

        System.out.println("\n");
        u.feature();
        ts.feature();
        s.feature();
    }
}