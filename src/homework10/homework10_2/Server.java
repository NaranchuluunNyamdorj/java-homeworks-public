package homework10.homework10_2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8888)) {
            System.out.println("Server is waiting for a connection...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Connected to client: " + socket.getInetAddress());

                new Thread(new ClientHandler(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public ClientHandler(Socket socket) {
        try {
            this.clientSocket = socket;
            this.inputStream = new DataInputStream(socket.getInputStream());
            this.outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Receive input from the client
                double operand1 = inputStream.readDouble();
                char operator = inputStream.readChar();
                double operand2 = inputStream.readDouble();

                // Perform the calculation
                double result = calculate(operand1, operator, operand2);

                // Send the result back to the client
                outputStream.writeDouble(result);
            }
        } catch (Exception e) {
            System.out.println("Client disconnected: " + clientSocket.getInetAddress());
        }
    }

    private double calculate(double operand1, char operator, double operand2) {
        double result = 0;

        switch (operator) {
            case '+':
                result = operand1 + operand2;
                break;
            case '-':
                result = operand1 - operand2;
                break;
            case '*':
                result = operand1 * operand2;
                break;
            case '/':
                if (operand2 != 0) {
                    result = operand1 / operand2;
                }
                break;
            case '%':
                if (operand2 != 0) {
                    result = operand1 % operand2;
                }
                break;
        }

        return result;
    }
}
