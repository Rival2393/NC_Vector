package vector.clientServer.server;

import vector.Vector;
import vector.Vectors;
import vector.exceptions.IncompatibleVectorSizesException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Dima on 28.11.2015.
 */
public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;

    public static void main(String[] args) {
        Server s = new Server();
        try {
            System.out.println("Server is Ready to run.");
            s.startServer();
            System.out.println("Server is Running!");
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
        catch (IncompatibleVectorSizesException e) {
            System.err.println("Vector sizes are incompatible");
        }
    }

    public void startServer()throws IOException, IncompatibleVectorSizesException {
        BufferedInputStream inputStream;
        BufferedOutputStream outputStream;
        Vector vector1;
        Vector vector2;

        serverSocket = new ServerSocket(9806);
        clientSocket = serverSocket.accept();
        inputStream = new BufferedInputStream(clientSocket.getInputStream());
        outputStream = new BufferedOutputStream(clientSocket.getOutputStream());

        while (true){
            try {
                vector1 = Vectors.inputVector(inputStream);
                vector2 = Vectors.inputVector(inputStream);
                vector1.sum(vector2);
                Vectors.outputVector(vector1, outputStream);
                outputStream.flush();
            }
            catch (IOException e) {
                outputStream.close();
                inputStream.close();
                clientSocket.close();
                serverSocket.close();
                break;
            }
        }
    }
}