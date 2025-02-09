import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Connected to server");

            // Input and Output streams for reading and writing data
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Separate thread for reading server messages
            Thread readThread = new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println("Server: " + serverMessage);
                    }
                } catch (IOException e) {
                    System.err.println("Error reading from server: " + e.getMessage());
                }
            }); 

            readThread.start();

            // Writing to server
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String clientMessage;
                while ((clientMessage = keyboard.readLine()) != null) {
                    out.println(clientMessage);
                }
            

            // Close connections
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
