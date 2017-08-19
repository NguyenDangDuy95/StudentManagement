package helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerConnection {
    public static ObjectInputStream ois = null;
    public static ObjectOutputStream oos = null;
    public static Socket socket;

    public static void connect() {
        try {
            System.out.println("Client 1 is ready");
            socket = new Socket("192.168.1.36", 5555);  
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.flush();
            System.out.println("ObjectOutput is ready");
            ois = new ObjectInputStream(socket.getInputStream());
            System.out.println("ObjectInput is ready");
        }catch(UnknownHostException e){
            System.out.println("Cannot connect to server!");
            return;
        }catch(IOException e){
            System.out.println("Cannot create I/O to server!");
            return;
        }
    }
}
