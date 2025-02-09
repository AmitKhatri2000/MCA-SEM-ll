import java.net.*;

public class Server {

public static void main(String[] args) {

try {

byte[] recivedData = new byte[1024];        
DatagramSocket serverSocket = new DatagramSocket(9876);
System.out.println("waiting for message...");
while (true) {

DatagramPacket recievedPacket = new DatagramPacket(recivedData , recivedData.length);
serverSocket.receive(recievedPacket);
String clientMsg =  new String(recievedPacket.getData() ,0, recievedPacket.getLength()); 
System.out.println("Sender: " + clientMsg);
if (clientMsg.equals("bye")) {
    System.out.println("Exiting...");
    break;
}
}

} catch (Exception e) {
    e.printStackTrace();
}       
}
}