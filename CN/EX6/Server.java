import java.io.*;
import java.net.*;

public class Server {

public static void main(String args[]){
try {
 DatagramSocket socket = new DatagramSocket(9876);
 byte[] receiveData = new byte[1024];

 int expectedSequenceNumber = 0;
 
 while (true) {

 DatagramPacket receivedPacket = new DatagramPacket(receiveData, receiveData.length );
 socket.receive(receivedPacket);  
 
 String msg = new String(receivedPacket.getData() , 0 , receivedPacket.getLength());
 String[] parts = msg.split(":");
 int sequenceNumber = Integer.parseInt(parts[0]);
 String data = parts[1];

 if (sequenceNumber == expectedSequenceNumber) {
        System.out.println("Frame received: " + data);
        expectedSequenceNumber = 1 - expectedSequenceNumber;
 }
 else{
    System.out.println("Duplicate frame received. Discarding...");
 }

 //aknowledgment
  String ack = "ACK:" + sequenceNumber;
  byte[] sendAckData = ack.getBytes();
  DatagramPacket sendAckPacket = new DatagramPacket(sendAckData, sendAckData.length , receivedPacket.getAddress() , receivedPacket.getPort());
  socket.send(sendAckPacket);
  System.out.println("ackownledgment sent:" + ack);
 }

} catch (Exception e) {
    e.printStackTrace();
}    
}    
}
