import java.io.IOException; 
import java.net.*;

public class Server {
public static void main(String[] args) throws IOException {
DatagramSocket socket = new DatagramSocket(9876);
byte[] data = new byte[65535];
DatagramPacket receivePacket = null;
System.out.println("server in waiting for a message...");
while (true) {
    
    receivePacket = new DatagramPacket(data, data.length);
    socket.receive(receivePacket);
    String msg = new String(receivePacket.getData() , 0 , receivePacket.getLength());
    System.out.println("client: "+ msg); 


if (msg.equals("bye")) {
    System.out.println("Exiting...");
    break;
}

data = new byte[65535];

}
   
}
}
    
