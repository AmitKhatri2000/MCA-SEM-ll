import java.net.*;
import java.io.IOException;
import java.util.Scanner;

public class Client{

    public static void main(String[] args) throws IOException
    {

        Scanner sc = new Scanner(System.in);
        DatagramSocket socket = new DatagramSocket();
        InetAddress ip = InetAddress.getLocalHost();
        byte buf[] = null;

        System.out.println("start conversation...");
        while (true) 
        {
        String msg = sc.nextLine();
        buf = msg.getBytes();
        DatagramPacket packet = new DatagramPacket( buf , buf.length , ip , 9876);
        socket.send(packet);
        if (msg.equals("bye")) break;
         }    
       } 
    }

