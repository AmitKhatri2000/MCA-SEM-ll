import java.io.*;
import java.net.*;


public class Client{

public static void main(String[] args) {
  
try{     
DatagramSocket clientSocket = new DatagramSocket(9877);
InetAddress ipAddress = InetAddress.getByName("localhost");
byte[] sendData = new byte[1024];
byte[] receiveData = new byte[1024];

Thread readThread = new Thread(()->{
    try {
        while (true) {
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        String serverMessage = new String(receivePacket.getData() , 0 ,  receivePacket.getLength());
        System.out.println("Server: " +serverMessage); 
        }
    } catch (Exception e) {
         System.err.println("Error in reading message from server: " + e.getMessage());
    }
    });
    readThread.start();


BufferedReader keyboard = new BufferedReader( new InputStreamReader(System.in));    
    while (true) {
    String clintMessage = keyboard.readLine();
    sendData = clintMessage.getBytes();
    DatagramPacket sendPacket = new DatagramPacket(sendData , sendData.length , ipAddress , 9876);   
    clientSocket.send(sendPacket);
    }

} catch (Exception e) {
    e.printStackTrace();
}
}
}

