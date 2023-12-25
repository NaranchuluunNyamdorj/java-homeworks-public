package homework10.homework10_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static final int PORT = 8888;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Server <number_of_voters>");
            return;
        }

        int numberOfVoters = Integer.parseInt(args[0]);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);

            Map<String, Integer> candidateVotes = new HashMap<>();
            candidateVotes.put("A", 0);
            candidateVotes.put("B", 0);

            for (int i = 0; i < numberOfVoters; i++) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                new Thread(new ClientHandler(clientSocket, candidateVotes)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;
    private Map<String, Integer> candidateVotes;

    public ClientHandler(Socket clientSocket, Map<String, Integer> candidateVotes) {
        try {
            this.clientSocket = clientSocket;
            this.reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.writer = new PrintWriter(clientSocket.getOutputStream(), true);
            this.candidateVotes = candidateVotes;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {

            writer.println("A");
            writer.println("B");

            String votedCandidate = reader.readLine();
            System.out.println("Received vote from " + ": " + votedCandidate);

            synchronized (candidateVotes) {
                candidateVotes.put(votedCandidate, candidateVotes.get(votedCandidate) + 1);
            }

            writer.println("Current Vote Counts:");
            writer.println("A: " + candidateVotes.get("A"));
            writer.println("B: " + candidateVotes.get("B"));

            writer.println("Voting has ended. Thank you for your participation!");

            System.out.println("Client disconnected: " + clientSocket.getInetAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
