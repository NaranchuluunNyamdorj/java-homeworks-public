package homework3.homework3_1;
public abstract class Vehicle {
    public abstract void hevleh();

    public static void main(String[] args) {
        Vehicle[] myVehicles = new Vehicle[3];
        Car car = new Car();
        myVehicles[0]=car;
        Bike bike = new Bike();
        myVehicles[1]=bike;
        Suih suih = new Suih();
        myVehicles[2] = suih;
        for (Vehicle myVehicle : myVehicles) {
            System.out.println("\n");
            myVehicle.hevleh();
        }
    }
}