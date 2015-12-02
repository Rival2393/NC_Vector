package vector.clientServer.client;

import vector.Vector;
import vector.Vectors;

import java.io.*;
import java.net.Socket;

/**
 * Created by Dima on 28.11.2015.
 */
public class Client {
    private Socket clientSocket;

    public static void main(String[] args) {
        Client c = new Client();
        try {
            c.startClient("C:/Activator/out.txt", "C:/Activator/in.txt");
        }
        catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void startClient(String input, String output) throws IOException {
        BufferedInputStream inputStream;
        BufferedOutputStream outputStream;
        FileReader fileReader;
        FileWriter fileWriter;
        Vector vector;

        clientSocket = new Socket("localhost", 9806);
        inputStream = new BufferedInputStream(clientSocket.getInputStream());
        outputStream = new BufferedOutputStream(clientSocket.getOutputStream());
        fileReader = new FileReader(input);
        fileWriter = new FileWriter(output);

        while (fileReader.ready()) {
            vector = Vectors.readVector(fileReader);
            Vectors.outputVector(vector, outputStream);
            vector = Vectors.readVector(fileReader);
            Vectors.outputVector(vector, outputStream);
            outputStream.flush();
            vector = Vectors.inputVector(inputStream);
            Vectors.writeVector(vector, fileWriter);
            outputStream.flush();
        }

        inputStream.close();
        outputStream.close();
        clientSocket.close();
    }
}
