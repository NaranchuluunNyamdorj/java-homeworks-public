package homework5;

import java.io.*;

public class BinaryFileReordering {
    public static void main(String[] args) {
        try {
            FileInputStream fileInputStream = new FileInputStream("f.dat");
            FileOutputStream fileOutputStream = new FileOutputStream("g.dat");

            byte[] buffer = new byte[4];
            int count = 0;

            while (fileInputStream.read(buffer) != -1) {
                fileOutputStream.write(buffer);
                if (count < 20) {
                    count++;
                } else {
                    fileOutputStream.flush();
                    fileOutputStream.close();

                    if (count == 40) {
                        break; //done
                    } else {
                        if (count % 20 == 0) { //change statues
                            fileOutputStream = new FileOutputStream("g.dat", true);
                            count = 0;
                        }
                    }
                }
            }
            fileInputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
