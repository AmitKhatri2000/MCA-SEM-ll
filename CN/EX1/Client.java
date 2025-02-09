import java.io.*;
import java.net.*;

public class Client {

    private Socket s = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;


    public Client(String host , int port){

     try{
        s = new Socket(host , port);
        System.out.println("connected");

        in = new DataInputStream(System.in);

        out = new DataOutputStream(s.getOutputStream());

     }
catch(UnknownHostException u){

System.out.println(u);
return;

}

catch(IOException i){

    System.out.println(i);
    return; 

}

String msg ="";

while (!msg.equals("Over")) {
    try {
        msg = in.readLine();
        out.writeUTF(msg);

    } catch (IOException i) {
        System.out.println(i);
    }

}

try {
    in.close();
    out.close();
    s.close();
} catch (IOException i) {
    System.out.println(i);
}
 }

public static void main(String[] args) {
 new Client("127.0.0.1" , 5000);
}


}


