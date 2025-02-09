import java.net.*;
import java.io.*;

public class Client{

public static void main(String args[]){ 
try {
 
    DatagramSocket clientSocket = new DatagramSocket();
    InetAddress ipAddress = InetAddress.getByName("localhost");
    byte[] sendData = new byte[1024];
    BufferedReader keyReader = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter messages to send to the receiver:");

    int sequenceNumber = 0;
    
    while (true) {
    String Msg = sequenceNumber +":"+ keyReader.readLine();
    sendData = Msg.getBytes();
    DatagramPacket SenderPacket = new DatagramPacket(sendData , sendData.length , ipAddress  , 9876);
    clientSocket.send(SenderPacket);
    System.out.println("frame sent: " + Msg);

    // ackownledgment
    
    byte[] recivedData = new byte[1024];
    DatagramPacket receivedPacket = new DatagramPacket(recivedData, recivedData.length);
    clientSocket.receive(receivedPacket);
    String ack = new String(receivedPacket.getData() ,0, receivedPacket.getLength());
    if (ack.equals("ACK:"+sequenceNumber)) {
        System.out.println("Acknowledgment received: "+ ack);
        sequenceNumber = 1 - sequenceNumber;
    }else{
            System.out.println("Acknowledgment failed or wrong sequence number. Resending frame...");
 }
    }
} catch (Exception e) {
    e.printStackTrace();
}
}
}

