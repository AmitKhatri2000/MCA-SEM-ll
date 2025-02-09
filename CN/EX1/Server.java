import java.net.*;
import java.io.*;

public class Server {

    private Socket s = null;
    private ServerSocket ss = null;
    private DataInputStream in = null;
 
    public Server(int port){
 
        try {
            ss = new ServerSocket(port);
            System.out.println("server started");
            System.out.println("Waiting for a client ...");
            s = ss.accept();

            in = new DataInputStream(
                new BufferedInputStream(s.getInputStream()));

                String msg = "";
              while(!msg.equals("Over"))
              {
                try{

                    msg = in.readUTF();
                    System.out.println(msg);
                }catch(IOException i){
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");
            s.close();
            in.close();
            } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
      new Server(5000);
    }

    
}