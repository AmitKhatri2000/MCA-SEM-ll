import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

public class Client{

public static void main(String[] args) {

try {
    DatagramSocket clientSocket = new DatagramSocket();
    byte[] sendData = new byte[1024];
    InetAddress ipAddress = InetAddress.getByName("localhost");
    BufferedReader keyboard = new BufferedReader( new InputStreamReader(System.in));
    System.out.println("enter the message to send");

 while (true) {
    
    String senderMsg = keyboard.readLine();
    sendData = senderMsg.getBytes();
    DatagramPacket senderPacket = new DatagramPacket(sendData , sendData.length , ipAddress , 9876);
    clientSocket.send(senderPacket);
 }

} catch (Exception e) {
    e.printStackTrace();
}    

}

}