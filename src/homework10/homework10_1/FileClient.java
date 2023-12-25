package homework10.homework10_1;

import java.io.*;
import java.net.Socket;

public class FileClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8888;
    private static final String DOWNLOAD_PATH = "downloaded_file.txt";

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
             InputStream inputStream = socket.getInputStream();
             BufferedOutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream(DOWNLOAD_PATH))) {

            byte[] buffer = new byte[1000];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }

            System.out.println("File downloaded successfully to: " + DOWNLOAD_PATH);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
