package homework10.homework10_2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8888);
             DataInputStream inputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
             Scanner scanner = new Scanner(System.in)) {

            while (true) {
                // Get input from the user
                System.out.print("Enter first number: ");
                double operand1 = scanner.nextDouble();

                System.out.print("Enter an operator (+, -, *, /, %): ");
                char operator = scanner.next().charAt(0);

                System.out.print("Enter second number: ");
                double operand2 = scanner.nextDouble();

                outputStream.writeDouble(operand1);
                outputStream.writeChar(operator);
                outputStream.writeDouble(operand2);
                outputStream.flush();

                double result = inputStream.readDouble();

                System.out.println("Result: " + result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
