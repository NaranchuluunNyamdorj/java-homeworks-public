package homework4.homework4_2;

public class CustomArray {
    private int[] array;
    private int size;
    public CustomArray() {
        array = new int[5];
        size = 0;
    }
    public void add(int value1, int value2) {
        try {
            int sum = Math.addExact(value1, value2);
            if (size == array.length) {
                increaseCapacity();
            }
            array[size] = sum;
            size++;
        } catch (ArithmeticException e) {
            System.out.println("OVERFLOW");
        }
    }
    public int at(int index) {
        try {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("Index out of bounds");
            }
            return array[index];
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
    private void increaseCapacity() {
        int newCapacity = array.length * 2;
        int[] newArray = new int[newCapacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    public static void main(String[] args) {
        CustomArray customArray = new CustomArray();
        customArray.add(10, 20);
        customArray.add(30, 40);

        customArray.add(Integer.MAX_VALUE, 1); //Overflow
        System.out.println("Element at index 0: " + customArray.at(0));
        System.out.println("Element at index 2: " + customArray.at(2)); //Index out of bound
    }
}
