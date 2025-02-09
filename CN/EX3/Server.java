import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server is listening on port 12345");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            // Input and Output streams for reading and writing data
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Separate thread for reading client messages
            Thread readThread = new Thread(() -> {
                try {
                    String clientMessage;
                    while ((clientMessage = in.readLine()) != null) {
                        System.out.println("Client: " + clientMessage);
                    }
                } catch (IOException e) {
                    System.err.println("Error reading from client: " + e.getMessage());
                }
            });

            readThread.start(); 

            // Writing to client
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String serverMessage;
            while ((serverMessage = keyboard.readLine()) != null) {
                out.println(serverMessage);
            }

            // Close connections
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
