import java.net.*;
import java.io.*;

public class Server{

public static void main(String[] args) {

try{ 
DatagramSocket servSocket = new DatagramSocket(9876);
byte[] sendData = new byte[1024];
byte[] receiveData = new byte[1024];
 
Thread readThread = new Thread(()-> {
try{
    while(true){
    DatagramPacket receivePacket = new DatagramPacket(receiveData , receiveData.length);
    servSocket.receive(receivePacket);
    String clintMessage = new String(receivePacket.getData() , 0 , receivePacket.getLength());
    System.out.println("client: " + clintMessage);
    }
}catch(Exception e){
    System.err.println("Error reading from client: " + e.getMessage());
}
});

readThread.start();


BufferedReader Keyboard = new BufferedReader(new InputStreamReader(System.in));
while (true) {
String serverMessage = Keyboard.readLine();
sendData = serverMessage.getBytes();
DatagramPacket sendPacket = new DatagramPacket(sendData , sendData.length , InetAddress.getByName("localhost") , 9877 );   
servSocket.send(sendPacket);
}
}catch(Exception e){
    e.printStackTrace();
}
}
}