package homework10.homework10_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8888;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Vote for 'A' or 'B': ");
            String vote = scanner.nextLine();
            writer.println(vote);

            System.out.println("Vote Counts:");
            String voteCounts;
            while (!(voteCounts = reader.readLine()).isEmpty()) {
                System.out.println(voteCounts);
            }

            String endMessage = reader.readLine();
            System.out.println(endMessage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
